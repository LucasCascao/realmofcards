package br.com.cascao.realmofcard.util.validador;

public class Util {

    public static String validarCampoString(Object objeto, String campo) {
        return objeto == null || objeto.toString().trim().equals("")? "O campo " + campo + " é obrigatório(a)." : "";
    }

    public String validarCampoDouble(Object objeto, String campo) {
        return isNull(objeto) || isEquals(objeto.toString(), "")
                || (objeto instanceof Double && isEquals(Double.parseDouble(objeto.toString()), 0.0)) ? "O campo " + campo + " é obrigatório(a)." : "";
    }

    public static Boolean isNull(Object o){ return o == null ? true : false; }

    public static Boolean isNotNull(Object o){ return o != null ? true : false; }

    public static Boolean isEquals(Object o1, Object o2){ return o1 == o2 ? true : false; }

    public static Boolean isNotEquals(Object o1, Object o2){
        return o1 != o2 ? true : false;
    }

    public static void main(String[] args) {
        System.out.println(isNotEquals("1","1"));
    }
}
