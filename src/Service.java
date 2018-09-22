
class Service implements Runnable {

    private int start;
    private int end;
    private boolean[] array;
    private int result;
    
    public Service(boolean[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    
    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        for(int i = start; i <= end; i++) {
            if(array[i]) {
                result++;
            }
        }
    }

}
