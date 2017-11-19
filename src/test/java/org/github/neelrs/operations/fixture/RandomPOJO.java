package org.github.neelrs.operations.fixture;

public class RandomPOJO {
    private Object field;

    public Object getField() {
        return field;
    }

    public void setField(final Object field) {
        this.field = field;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof RandomPOJO)) return false;

        final RandomPOJO that = (RandomPOJO) o;

        return field != null ? field.equals(that.field) : that.field == null;
    }

    @Override
    public int hashCode() {
        return field != null ? field.hashCode() : 0;
    }
}
