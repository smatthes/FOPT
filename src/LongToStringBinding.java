import javafx.beans.binding.StringBinding;
import javafx.beans.property.LongProperty;

class LongToStringBinding extends StringBinding {

    private LongProperty property;

    public LongToStringBinding(LongProperty property) {
        bind(property);
        this.property = property;
    }

    @Override
    protected String computeValue() {
        return Long.toString(property.get());
    }

}
