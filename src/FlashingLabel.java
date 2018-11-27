import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class FlashingLabel extends Label {

    private LongProperty delay;

    private Thread blinker;

    public FlashingLabel() {
        super();
        initialize();
    }

    public FlashingLabel(Long delay) {
        this();

        if (delay > 0) {
            this.delay.set(delay);
        }
    }

    public FlashingLabel(String text) {
        super(text);
        initialize();
    }

    public FlashingLabel(String text, Long delay) {
        this(text);

        if (delay > 0) {
            this.delay.set(delay);
        }
    }

    public FlashingLabel(String text, Node graphic) {
        super(text, graphic);
        initialize();
    }

    public FlashingLabel(String text, Node graphic, Long delay) {
        this(text, graphic);

        if (delay > 0) {
            this.delay.set(delay);
        }
    }

    private void initialize() {
        if (delay == null) {
            delay = new SimpleLongProperty(500L);
        }
        blinker = new Thread(() -> blinker());
        blinker.start();
    }

    private void blinker() {
        while (!blinker.isInterrupted()) {
            Platform.runLater(() -> {
                setVisible(!isVisible());
            });

            try {
                Thread.sleep(delay.get());
            } catch (InterruptedException ex) {
                blinker.interrupt();
            }
        }
    }

    public long getDelay() {
        return delay.get();
    }

    public void setDelay(long delay) {
        this.delay.set(delay);
    }

    public LongProperty delayProperty() {
        return delay;
    }

    public void stopBlinking() {
        if (!Thread.interrupted()) {
            blinker.interrupt();
        }

        setVisible(true);
    }

}
