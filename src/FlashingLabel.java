import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class FlashingLabel extends Label {

    private LongProperty delay;

    private Thread flasher;

    public FlashingLabel() {
        super();
        initialize();
    }

    public FlashingLabel(Long delay) {
        this();
        setDelay(delay);
    }

    public FlashingLabel(String text) {
        super(text);
        initialize();
    }

    public FlashingLabel(String text, Long delay) {
        this(text);
        setDelay(delay);
    }

    public FlashingLabel(String text, Node graphic) {
        super(text, graphic);
        initialize();
    }

    public FlashingLabel(String text, Node graphic, Long delay) {
        this(text, graphic);
        setDelay(delay);
    }

    private void initialize() {
        if (delay == null) {
            delay = new SimpleLongProperty(500L);
        }
        flasher = new Thread(() -> flashing());
        flasher.setDaemon(true);
        flasher.start();
    }

    private void flashing() {
        while (!flasher.isInterrupted()) {
            Platform.runLater(() -> {
                setVisible(!isVisible());
            });

            try {
                Thread.sleep(delay.get());
            } catch (InterruptedException ex) {
                flasher.interrupt();
            }
        }
    }

    public long getDelay() {
        return delay.get();
    }

    public void setDelay(long delay) {
        if (delay > 0) {
            this.delay.set(delay);
        }
    }

    public LongProperty delayProperty() {
        return delay;
    }

    public void stopFlashing() {
        if (!Thread.interrupted()) {
            flasher.interrupt();
        }

        setVisible(true);
    }

}
