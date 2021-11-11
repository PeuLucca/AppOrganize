package helper;

import java.text.SimpleDateFormat;

public class DateUtil {

    public static String dataAtual(){
        long date = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat( "dd/MM/yyyy" );
        String dataString = simpleDateFormat.format( date );

        return dataString;
    }

    public static String mesAnoDataEscolhida(String data){

        //23/01/2018
        String retornoData[] = data.split( "/" );
        String dia = retornoData[0];
        String mes = retornoData[1];
        String ano = retornoData[2];

        String date = mes + ano; // exemplo: 25/04/2006 --> date = 042006

        return date;

    }

}
