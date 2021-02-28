package com.company;

/***
 * interfata Classifiable. Obliga clasele ce o implementeaza sa suprascrie TOATE metodele din ea (cu exceptia cand clasa e abstracta).
 */
public interface Classifiable {

    public boolean isClassifiable();
    public int getRank();
}
