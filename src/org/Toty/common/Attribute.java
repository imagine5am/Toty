package org.Toty.common;

import java.io.Serializable;

/**
 *
 * @author Shivam Sood
 */
public class Attribute implements Serializable{
    private String attributeName;
    private String attributeValue;
    public Attribute(){
        attributeName=new String();
        attributeValue=new String();
    }
    
    public Attribute(String attributeName,String attributeValue){
        this.attributeName=attributeName;
        this.attributeValue=attributeValue;
    }
    
    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }
}
