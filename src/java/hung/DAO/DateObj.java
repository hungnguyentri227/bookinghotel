/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hung.DAO;

/**
 *
 * @author hungn
 */
public class DateObj {

    private String datebegin;
    private String dateend;
    private long count;

    public DateObj() {
    }

    public DateObj(String datebegin, String dateend, long count) {
        this.datebegin = datebegin;
        this.dateend = dateend;
        this.count = count;
    }

    public String getDatebegin() {
        return datebegin;
    }

    public void setDatebegin(String datebegin) {
        this.datebegin = datebegin;
    }

    public String getDateend() {
        return dateend;
    }

    public void setDateend(String dateend) {
        this.dateend = dateend;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}
