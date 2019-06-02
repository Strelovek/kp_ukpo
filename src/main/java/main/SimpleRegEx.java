package main;
import javafx.util.Pair;
import java.util.ArrayList;

public class SimpleRegEx {
    private ArrayList<ArrayList<Pair<Character, Integer>>> parsetable;
    private int fs;
    public int indexOf(String reg, String src) {
        int answ = -1;
        System.out.println("regex : " + checkRegEx(reg));
        if (checkRegEx(reg)) {
            createParseTable(reg);
            for (int i = 0; i < src.length(); i++) {
                if (parse(src, i)) {
                    answ = i;
                    return answ;
                }
            }
        }
        return answ;
    }

    public String findAll(String reg, String src) {
        int answ = -1;
        String w=new String();
        System.out.println("regex : " + checkRegEx(reg));
        if (checkRegEx(reg)) {
            createParseTable(reg);
            for (int i = 0; i < src.length(); i++) {
                if (parse(src, i)) {
                    w=w+";"+Integer.toString(i);

                }
            }
        }
        return w;
    }

    private void createParseTable(String reg) {
        parsetable = new ArrayList<ArrayList<Pair<Character, Integer>>>();
        char s;
        ArrayList<Pair<Character, Integer>> tmp;
        Pair<Character, Integer> t;
        fs = 0;
        for (int i = 0; i < reg.length(); i++) {
            s = reg.charAt(i);
            System.out.println("cur ch = "+s);
            if ((s >= 'a' && s <= 'z') || (s >= 'A' && s <= 'Z') || s == ' ') {
                t = new Pair<Character, Integer>(s, i + 1);
                tmp = new ArrayList<Pair<Character, Integer>>();
                tmp.add(t);
                parsetable.add(tmp);
                System.out.println("st = "+i+" rule "+t);
            }

            if (s == '.') {
                t = new Pair<Character, Integer>('$', i + 1);
                tmp = new ArrayList<Pair<Character, Integer>>();
                tmp.add(t);
                System.out.println("st = "+i+" rule "+t);
                parsetable.add(tmp);
            }

            if (s == '+') {//1+
                t = new Pair<Character, Integer>(parsetable.get(parsetable.size()-1).get(0).getKey(), i);
                System.out.println("st = "+i+" rule "+t);
                tmp = new ArrayList<Pair<Character, Integer>>();
                tmp.add(t);
                if(i<=reg.length()-2){
                    Pair<Character, Integer> t2 = new Pair<Character, Integer>(reg.charAt(i+1), i + 1);
                    System.out.println("st = "+(i+1)+" rule "+t2);
                    tmp.add(t2);
                    i++;
                }
                parsetable.add(tmp);
            }

            if (s == '*') {//0+
                tmp = new ArrayList<Pair<Character, Integer>>();
                t = new Pair<Character, Integer>(parsetable.get(parsetable.size()-1).get(0).getKey(), i-1);
                System.out.println("st = "+i+" rule "+t);
                tmp.add(t);
                parsetable.add(tmp);
                if (i<=reg.length()-2) {
                    Pair<Character, Integer> t2 = new Pair<Character, Integer>(reg.charAt(i+1), i +1);
                    System.out.println("st = "+(i-1)+" rule "+t);
                    System.out.println("st = "+i+" rule "+t);
                    parsetable.get(i).add(t2);
                    i++;
                }
            }
        }

        for (ArrayList<Pair<Character, Integer>> i : parsetable) {
            for (Pair<Character, Integer> j : i)
                if(fs<j.getValue())
                    fs=j.getValue();
        }
        int stt = 0;
        System.out.println("размер тб : " + parsetable.size());
        for (ArrayList<Pair<Character, Integer>> i : parsetable) {
            for (Pair<Character, Integer> j : i)
                System.out.println("stste = " + stt + " pair = " + j.getKey() + " " + j.getValue() + " fs = " +fs);
            stt++;
        }
        System.out.println("====================================");
    }

    private boolean parse(String src, int beg) {
        /*  "." – любой символ
         *  "*" - 0 и более символов,
         *  "+" - 1 и более символов
         *  */
        if(beg<0)
            return false;
        System.out.println("====================================");
        int state = 0, i = beg;
        char ch = src.charAt(i);
        while (ch != '#') {
            ArrayList<Pair<Character, Integer>> parseState;
            if (state >= 0 && state < parsetable.size())
                parseState = parsetable.get(state);
            else
                parseState = new ArrayList<Pair<Character, Integer>>();
            for (Pair<Character, Integer> j : parseState) {
                System.out.println("cur state = " + state + " cur sumb = " + ch);
                if (j.getKey() == '$'||j.getKey() == '.') {
                    state = j.getValue();
                } else if (j.getKey() == ch) {
                    state = j.getValue();
                } else {
                   state = -1;
                }
                System.out.println("new state = " + state);
            }
                ch = src.charAt(++i);
        }
        System.out.println("final state : " + Integer.toString(state) + " fs = " );
            if (state == fs)
                return true;
        return false;
    }

    private boolean checkRegEx(String src) {
        char s;
        int i = 0, st = 0;
        while (i < src.length()) {
            s = src.charAt(i);
            switch (st) {
                case 0:
                    if (s == '.')
                        st = 2;
                    else if ((s >= 'a' && s <= 'z') || (s >= 'A' && s <= 'Z') || s == ' ')
                        st = 1;
                    else
                        return false;
                    break;
                case 1:
                    if (s == '.')
                        st = 2;
                    else if ((s >= 'a' && s <= 'z') || (s >= 'A' && s <= 'Z') || s == ' ')
                        st = 1;
                    else if (s == '*')
                        st = 3;
                    else if (s == '+')
                        st = 4;
                    else
                        return false;
                    break;
                case 2:
                    if (s == '.')
                        st = 2;
                    else if ((s >= 'a' && s <= 'z') || (s >= 'A' && s <= 'Z') || s == ' ')
                        st = 1;
                    else if (s == '*')
                        st = 3;
                    else if (s == '+')
                        st = 4;
                    else
                        return false;
                    break;
                case 3:
                    if (s == '.')
                        st = 2;
                    else if ((s >= 'a' && s <= 'z') || (s >= 'A' && s <= 'Z') || s == ' ')
                        st = 1;
                    else
                        return false;
                    break;
                case 4:
                    if (s == '.')
                        st = 2;
                    else if ((s >= 'a' && s <= 'z') || (s >= 'A' && s <= 'Z') || s == ' ')
                        st = 1;
                    else
                        return false;
                    break;
            }
            i++;
        }

        if (st >= 1 && st <= 4)
            return true;
        else
            return false;
    }
}
