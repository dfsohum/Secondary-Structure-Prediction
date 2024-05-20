import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.text.DecimalFormat;
public class AAA
{
    public static void main(String args[])
    {
        mainloop:
        while(true)
        {
            System.out.println("Enter the amino acid sequence:");
            Scanner in = new Scanner(System.in);
            String inp = in.nextLine();
            AAA.Scale(inp);
            while(true)
            {
                System.out.println("\nDo you want to run again?");
                inp = in.next();
                if(inp.equalsIgnoreCase("no"))
                {
                    System.out.println("Thank you.");
                    break mainloop;
                }
                else if(inp.equalsIgnoreCase("yes"))
                {
                    break;
                }
                else
                {
                    System.out.println("Invalid character.");
                }
            }
        }
    }
    static void Scale(String inp)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Which hydrophbicity scale would you like to choose for the calculation?");
        System.out.println("1. None: Hydrophobicity will be calculated on basis of chemical structure.");
        System.out.println("2. Nozaki-Tanford-Jones.");
        System.out.println("3. Ponnuswamy-Gromiha.");
        System.out.println("4. User-defined.");
        String ch = in.nextLine();
        switch(ch)
        {
            case "1":
            AAA.Converter(inp);
            break;
            case "2":
            AAA.ConverterNTJ(inp);
            break;
            case "3":
            AAA.ConverterPG(inp);
            break;
            case "4":
            AAA.UD(inp);
            break;
            default:
            System.out.println("Invalid choice.");
            AAA.Scale(inp);
            break;
            }
    }
    static void UD(String inp)
    {
        Scanner in = new Scanner(System.in);
        int i, j;
        double m = 0.0;
        String c = "";
        int l = inp.length();
        String[] sc = {"G","A","V","L","I","M","F","W","P","C","D","E","K","N","Q","R","S","T","Y","H"};
        Double[] val = new Double[sc.length];
        Double[] scv = new Double[l];
        System.out.println("Enter the values in decimal format.");
        for(i=0; i<sc.length; i++)
        {
            while(true)
            {
                try
                {
                    System.out.print("Enter the value of '" + sc[i] + "': ");
                    val[i] = Double.valueOf(in.next());
                    break;
                }
                catch(Exception e)
                {  
                    System.out.println("Invalid value, enter again. ");
                }
            }
        }
        for(i=0; i<l; i++)
        {
            c = String.valueOf(inp.charAt(i));
            for(j=0; j<sc.length; j++)
            {
                if(c.equals(sc[j]))
                {
                    scv[i] = val[j];
                    break;
                }
            }
        }
        AAA.Pattern1(scv);
    }
    static void Converter(String inp)
    {
        int i;
        String c = "";
        int l = inp.length();
        String con = "";
        String[] hphobic = {"G","A","V","L","I","M","F","W","P"};
        String[] hphilic = {"C","D","E","K","N","Q","R","S","T","Y","H"};
        List hphobicList = Arrays.asList(hphobic);
        List hphilicList = Arrays.asList(hphilic);
        for(i=0; i<l; i++)
        {
            c = String.valueOf(inp.charAt(i));
            if(hphobicList.contains(c))
            {
                con = con + "X";
            }
            else if(hphilicList.contains(c))
            {
                con = con + "O";
            }
            else
            {
                System.out.println("Invalid character.");
                return;
            }
        }
        System.out.println("Converted AA into hydrophilic and hydrophobic clusters:");
        System.out.println("X: Hydrophobic");
        System.out.println("O: Hydrophilic");
        System.out.println(con);
        AAA.Pattern2(con);
    }
    static void ConverterNTJ(String inp)
    {
        int i, j;
        double m = 0.0;
        double sum = 0.0;
        String c = "";
        int l = inp.length();
        String con = "";
        String[][] NTJ = {{"G","A","V","L","I","M","F","W","P",
                           "C","D","E","K","N","Q","R","S","T","Y","H"},
                          {"0.10","0.87","1.87","2.17","3.15","1.67","2.87","3.77","2.77",
                           "1.52","0.66","0.67","1.64","0.09","0.00","0.85","0.07","0.07","2.67","0.87"}};
        Double[] ntjn = new Double[l];
        for(i=0; i<l; i++)
        {
            c = String.valueOf(inp.charAt(i));
            for(j=0; j<20; j++)
            {
                if(c.equals(NTJ[0][j]))
                {
                     ntjn[i] = Double.valueOf((NTJ[1][j]));
                    break;
                }
            }
        }
        AAA.Pattern1(ntjn);
    }
    static void ConverterPG(String inp)
    {
        int i, j;
        double m = 0.0;
        double sum = 0.0;
        String c = "";
        int l = inp.length();
        String con = "";
        String[][] PG = {{"G","A","V","L","I","M","F","W","P",
                           "C","D","E","K","N","Q","R","S","T","Y","H"},
                          {"13.34","13.85","14.56","14.13","15.28","13.86","13.93","15.48","12.35",
                           "15.37","11.61", "11.38","11.58","13.02","12.61","13.10","13.39","12.70","13.88","13.82"}};
        Double[] pgn = new Double[l];
        for(i=0; i<l; i++)
        {
            c = String.valueOf(inp.charAt(i));
            for(j=0; j<20; j++)
            {
                if(c.equals(PG[0][j]))
                {
                    pgn[i] = Double.valueOf((PG[1][j]));
                    break;
                }
            }
        }
        AAA.Pattern1(pgn);
    }
    static void Pattern1(Double[] ret)
    {
        int i;
        double m = 0.0;
        double sum = 0.0;
        double avg = 0.0;
        String c = "";
        int l = ret.length;
        String con = "";
        for(i=0; i<l; i++)
        {
            sum = sum + ret[i];
        }
        avg = sum/l;
        for(i=0; i<l; i++)
        {
            m = ret[i];
            if(m>=avg)
            {
                con = con + "X";
            }
            else if(m<avg)
            {
                con = con + "O";
            }
        }
        System.out.println("Converted AA into hydrophilic and hydrophobic clusters:");
        System.out.println("X: Hydrophobic");
        System.out.println("O: Hydrophilic");
        System.out.println(con);
        AAA.Pattern2(con);
    }
    static void Pattern2(String xon)
    {
        DecimalFormat ro = new DecimalFormat("#.####");
        int i;
        double a1 = 0.0;
        double b1 = 0.0;
        int l = xon.length();
        String con2 = "";
        for(i=0; i<(xon.length()-1); i++)
        {
            if((xon.charAt(i)=='X'&&xon.charAt(i+1)=='O')||(xon.charAt(i)=='O'&&xon.charAt(i+1)=='X'))
            {
                con2 = con2 + "A";
                a1++;
            }
            if((xon.charAt(i)=='X'&&xon.charAt(i+1)=='X')||(xon.charAt(i)=='O'&&xon.charAt(i+1)=='O'))
            {
                con2 = con2 + "B";
                b1++;
            }
        }
        if((xon.charAt(l-1)=='X'&&xon.charAt(l-2)=='O')||(xon.charAt(l-1)=='O'&&xon.charAt(l-2)=='X'))
        {
            con2 = con2 + "A";
            a1++;
        }
        else
        {
            con2 = con2 + "B";
            b1++;
        }
        System.out.println("Peptide chain visualised as alpha helices (A) and beta sheets (B):");
        System.out.println(con2);
        System.out.println("Alpha character(Percentage of alpha helices): " + ro.format((a1/(a1+b1)*100)) + "%");
        System.out.println("Beta character(Percentage of beta sheets): " + ro.format((b1/(a1+b1)*100)) + "%");
    }
}  