package br.edu.les.realmofcard.util;

public class Util {

    public static Boolean isNull(Object o){ return o == null ? true : false; }

    public static Boolean isNotNull(Object o){ return o != null ? true : false; }

    public static Boolean isEquals(Object o1, Object o2){ return o1 == o2 ? true : false; }

    public static Boolean isNotEquals(Object o1, Object o2){
        return o1 != o2 ? true : false;
    }

    public static Boolean isBigger(Long l1, Long l2){ return l1 > l2 ? true : false; }

    public static Boolean isBiggerOrEquals(Long l1, Long l2){ return l1 >= l2 ? true : false; }

    public static Boolean isSmaller(Long l1, Long l2){ return l1 < l2 ? true : false; }

    public static Boolean isSmallerOrEquals(Long l1, Long l2){ return l1 <= l2 ? true : false; }
}
