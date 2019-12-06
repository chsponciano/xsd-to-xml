package model;

import utils.Formatting;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class MirrorClass {
    private String label;
    private Class type;
    private Object object;

    public MirrorClass(String label, Class type) throws IllegalAccessException, InstantiationException {
        this.setLabel(label);
        this.setType(type);
        this.setObject(type.newInstance());
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Class getType() {
        return this.type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Object getObject() {
        return this.object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void addAttributeObject(final String attributeName, final Object attributeObject)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.type.getMethod(Formatting.setMethodName(attributeName),
                new Class[] {attributeObject.getClass()}).invoke(this.object, attributeObject);
    }

    public String[] getClassAttributes() {
        String temp;
        for (Annotation annotation : this.type.getAnnotations()) {
            temp = annotation.toString();
            if (temp.contains("propOrder")) {
                temp = temp.substring(temp.indexOf("propOrder") + 11);
                temp = temp.substring(0, temp.indexOf("]")).trim();
                return temp.split(", ");
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Element: ".concat(this.label) +
                " - ClassCurrent : ".concat(this.type.toString());

    }
}
