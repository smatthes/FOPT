public class AsyncRequest {
    
    private static final int ARRAY_SIZE = 1_000_000_000;
    
    private static final int NUMBER_OF_SERVERS = 20;

    public static void main(String[] args) {
        // Erzeuge Feld mit jedem 10. Wert true
        boolean array[] = new boolean[ARRAY_SIZE];
        
        for(int i=0; i<ARRAY_SIZE; i++) {
            if(Math.random() < 0.1) {
                array[i] = true;
            }
            else {
                array[i] = false;
            }
        }
        
        // Startzeit messen
        long startTime = System.currentTimeMillis();
        
        // Feld für Services und Threads erzeugen
        Service[] services = new Service[NUMBER_OF_SERVERS];
        Thread[] serverThreads = new Thread[NUMBER_OF_SERVERS];
        
        // Threads erzeugen
        int start = 0;
        int end;
        int howMany = ARRAY_SIZE / NUMBER_OF_SERVERS;
        
        for(int i=0; i<NUMBER_OF_SERVERS; i++) {
            if(i < NUMBER_OF_SERVERS-1) {
                end = start + howMany - 1;
            }
            else {
                end = ARRAY_SIZE - 1;
            }
            
            services[i] = new Service(array, start, end);
            serverThreads[i] = new Thread(services[i]);
            serverThreads[i].start();
            start = end + 1;
        }
        
        // Synchronisation mit Servern (auf Ende warten)
        for(Thread thread : serverThreads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.getLocalizedMessage();
            }            
        }
        
        // Gesamtergebnis aus Teilergebnissen berechnen
        int result = 0;
        
        for(Service service : services) {
            result += service.getResult();
        }
        
        // Endzeit messen
        long endTime = System.currentTimeMillis();
        float time = (endTime - startTime) / 1_000.0f;
        System.out.println("Rechenzeit: " + time);
        
        // Ergebnis ausgeben
        System.out.println("Ergebnis: " + result);
    }

}
