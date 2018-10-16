
package org.apache.hello_world_soap_http.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="minor" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *         &lt;element name="major" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "minor",
    "major"
})
@XmlRootElement(name = "faultDetail")
public class FaultDetail {

    protected short minor;
    protected short major;

    /**
     * ��ȡminor���Ե�ֵ��
     * 
     */
    public short getMinor() {
        return minor;
    }

    /**
     * ����minor���Ե�ֵ��
     * 
     */
    public void setMinor(short value) {
        this.minor = value;
    }

    /**
     * ��ȡmajor���Ե�ֵ��
     * 
     */
    public short getMajor() {
        return major;
    }

    /**
     * ����major���Ե�ֵ��
     * 
     */
    public void setMajor(short value) {
        this.major = value;
    }

}
