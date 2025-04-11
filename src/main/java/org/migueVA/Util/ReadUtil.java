package org.migueVA.Util;
import java.util.Scanner;

public class ReadUtil
{
    private Scanner scanner;
    private static ReadUtil readUtil;

    private ReadUtil()
    {
        scanner = new Scanner( System.in );
    }

    public Scanner getScanner()
    {
        return scanner;
    }

    public static ReadUtil getInstance( )
    {
        if(readUtil==null)
        {
            readUtil = new ReadUtil();
        }
        return readUtil;
    }

    public static String read( )
    {
        return getInstance( ).getScanner( ).nextLine();
    }

    public static Integer readInt( )
    {
        String valor = null;
        boolean flag = true;
        Integer aux = null;

        while (flag)
        {
            valor = read();
            if (valor != null && !valor.isEmpty())
            {
                try
                {
                    aux = Integer.valueOf(valor);
                    if (aux != null)
                    {
                        return aux;
                    }
                }
                catch (Exception e)
                {
                }
            }
            System.out.println( "Valor incorrecto, intentelo nuevamente" );
        }
        return null;
    }

    public static Float readFloat() {
        String valor;
        boolean flag = true;
        Float aux = null;

        while (flag) {
            valor = read();
            if (valor != null && !valor.isEmpty()) {
                try {
                    aux = Float.valueOf(valor);
                    return aux;
                } catch (Exception e) {
                    // Ignorar la excepción y mostrar mensaje de error
                }
            }
            System.out.println("Valor incorrecto, inténtelo nuevamente");
        }
        return null;
    }

    public static Integer string2Integer( String valor )
    {
        try
        {
            return Integer.valueOf(valor);
        }
        catch (Exception e)
        {
        }
        return null;
    }

    public static Double string2Double( String valor )
    {
        try
        {
            return Double.valueOf(valor);
        }
        catch (Exception e)
        {
        }
        return null;
    }
}
