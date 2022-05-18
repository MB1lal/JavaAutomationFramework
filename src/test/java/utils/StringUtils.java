package utils;

import java.util.HashMap;
import java.util.Map;

public class StringUtils {
    static Map<String, Character> escapeStrings;

    static {
        // HTML character entity references as defined in HTML 4
        // see http://www.w3.org/TR/REC-html40/sgml/entities.html
        escapeStrings = new HashMap<>(252);

        escapeStrings.put("&nbsp;", '\u00A0');
        escapeStrings.put("&iexcl;", '\u00A1');
        escapeStrings.put("&cent;", '\u00A2');
        escapeStrings.put("&pound;", '\u00A3');
        escapeStrings.put("&curren;", '\u00A4');
        escapeStrings.put("&yen;", '\u00A5');
        escapeStrings.put("&brvbar;", '\u00A6');
        escapeStrings.put("&sect;", '\u00A7');
        escapeStrings.put("&uml;", '\u00A8');
        escapeStrings.put("&copy;", '\u00A9');
        escapeStrings.put("&ordf;", '\u00AA');
        escapeStrings.put("&laquo;", '\u00AB');
        escapeStrings.put("&not;", '\u00AC');
        escapeStrings.put("&shy;", '\u00AD');
        escapeStrings.put("&reg;", '\u00AE');
        escapeStrings.put("&macr;", '\u00AF');
        escapeStrings.put("&deg;", '\u00B0');
        escapeStrings.put("&plusmn;", '\u00B1');
        escapeStrings.put("&sup2;", '\u00B2');
        escapeStrings.put("&sup3;", '\u00B3');
        escapeStrings.put("&acute;", '\u00B4');
        escapeStrings.put("&micro;", '\u00B5');
        escapeStrings.put("&para;", '\u00B6');
        escapeStrings.put("&middot;", '\u00B7');
        escapeStrings.put("&cedil;", '\u00B8');
        escapeStrings.put("&sup1;", '\u00B9');
        escapeStrings.put("&ordm;", '\u00BA');
        escapeStrings.put("&raquo;", '\u00BB');
        escapeStrings.put("&frac14;", '\u00BC');
        escapeStrings.put("&frac12;", '\u00BD');
        escapeStrings.put("&frac34;", '\u00BE');
        escapeStrings.put("&iquest;", '\u00BF');
        escapeStrings.put("&Agrave;", '\u00C0');
        escapeStrings.put("&Aacute;", '\u00C1');
        escapeStrings.put("&Acirc;", '\u00C2');
        escapeStrings.put("&Atilde;", '\u00C3');
        escapeStrings.put("&Auml;", '\u00C4');
        escapeStrings.put("&Aring;", '\u00C5');
        escapeStrings.put("&AElig;", '\u00C6');
        escapeStrings.put("&Ccedil;", '\u00C7');
        escapeStrings.put("&Egrave;", '\u00C8');
        escapeStrings.put("&Eacute;", '\u00C9');
        escapeStrings.put("&Ecirc;", '\u00CA');
        escapeStrings.put("&Euml;", '\u00CB');
        escapeStrings.put("&Igrave;", '\u00CC');
        escapeStrings.put("&Iacute;", '\u00CD');
        escapeStrings.put("&Icirc;", '\u00CE');
        escapeStrings.put("&Iuml;", '\u00CF');
        escapeStrings.put("&ETH;", '\u00D0');
        escapeStrings.put("&Ntilde;", '\u00D1');
        escapeStrings.put("&Ograve;", '\u00D2');
        escapeStrings.put("&Oacute;", '\u00D3');
        escapeStrings.put("&Ocirc;", '\u00D4');
        escapeStrings.put("&Otilde;", '\u00D5');
        escapeStrings.put("&Ouml;", '\u00D6');
        escapeStrings.put("&times;", '\u00D7');
        escapeStrings.put("&Oslash;", '\u00D8');
        escapeStrings.put("&Ugrave;", '\u00D9');
        escapeStrings.put("&Uacute;", '\u00DA');
        escapeStrings.put("&Ucirc;", '\u00DB');
        escapeStrings.put("&Uuml;", '\u00DC');
        escapeStrings.put("&Yacute;", '\u00DD');
        escapeStrings.put("&THORN;", '\u00DE');
        escapeStrings.put("&szlig;", '\u00DF');
        escapeStrings.put("&agrave;", '\u00E0');
        escapeStrings.put("&aacute;", '\u00E1');
        escapeStrings.put("&acirc;", '\u00E2');
        escapeStrings.put("&atilde;", '\u00E3');
        escapeStrings.put("&auml;", '\u00E4');
        escapeStrings.put("&aring;", '\u00E5');
        escapeStrings.put("&aelig;", '\u00E6');
        escapeStrings.put("&ccedil;", '\u00E7');
        escapeStrings.put("&egrave;", '\u00E8');
        escapeStrings.put("&eacute;", '\u00E9');
        escapeStrings.put("&ecirc;", '\u00EA');
        escapeStrings.put("&euml;", '\u00EB');
        escapeStrings.put("&igrave;", '\u00EC');
        escapeStrings.put("&iacute;", '\u00ED');
        escapeStrings.put("&icirc;", '\u00EE');
        escapeStrings.put("&iuml;", '\u00EF');
        escapeStrings.put("&eth;", '\u00F0');
        escapeStrings.put("&ntilde;", '\u00F1');
        escapeStrings.put("&ograve;", '\u00F2');
        escapeStrings.put("&oacute;", '\u00F3');
        escapeStrings.put("&ocirc;", '\u00F4');
        escapeStrings.put("&otilde;", '\u00F5');
        escapeStrings.put("&ouml;", '\u00F6');
        escapeStrings.put("&divide;", '\u00F7');
        escapeStrings.put("&oslash;", '\u00F8');
        escapeStrings.put("&ugrave;", '\u00F9');
        escapeStrings.put("&uacute;", '\u00FA');
        escapeStrings.put("&ucirc;", '\u00FB');
        escapeStrings.put("&uuml;", '\u00FC');
        escapeStrings.put("&yacute;", '\u00FD');
        escapeStrings.put("&thorn;", '\u00FE');
        escapeStrings.put("&yuml;", '\u00FF');
        escapeStrings.put("&fnof;", '\u0192');
        escapeStrings.put("&Alpha;", '\u0391');
        escapeStrings.put("&Beta;", '\u0392');
        escapeStrings.put("&Gamma;", '\u0393');
        escapeStrings.put("&Delta;", '\u0394');
        escapeStrings.put("&Epsilon;", '\u0395');
        escapeStrings.put("&Zeta;", '\u0396');
        escapeStrings.put("&Eta;", '\u0397');
        escapeStrings.put("&Theta;", '\u0398');
        escapeStrings.put("&Iota;", '\u0399');
        escapeStrings.put("&Kappa;", '\u039A');
        escapeStrings.put("&Lambda;", '\u039B');
        escapeStrings.put("&Mu;", '\u039C');
        escapeStrings.put("&Nu;", '\u039D');
        escapeStrings.put("&Xi;", '\u039E');
        escapeStrings.put("&Omicron;", '\u039F');
        escapeStrings.put("&Pi;", '\u03A0');
        escapeStrings.put("&Rho;", '\u03A1');
        escapeStrings.put("&Sigma;", '\u03A3');
        escapeStrings.put("&Tau;", '\u03A4');
        escapeStrings.put("&Upsilon;", '\u03A5');
        escapeStrings.put("&Phi;", '\u03A6');
        escapeStrings.put("&Chi;", '\u03A7');
        escapeStrings.put("&Psi;", '\u03A8');
        escapeStrings.put("&Omega;", '\u03A9');
        escapeStrings.put("&alpha;", '\u03B1');
        escapeStrings.put("&beta;", '\u03B2');
        escapeStrings.put("&gamma;", '\u03B3');
        escapeStrings.put("&delta;", '\u03B4');
        escapeStrings.put("&epsilon;", '\u03B5');
        escapeStrings.put("&zeta;", '\u03B6');
        escapeStrings.put("&eta;", '\u03B7');
        escapeStrings.put("&theta;", '\u03B8');
        escapeStrings.put("&iota;", '\u03B9');
        escapeStrings.put("&kappa;", '\u03BA');
        escapeStrings.put("&lambda;", '\u03BB');
        escapeStrings.put("&mu;", '\u03BC');
        escapeStrings.put("&nu;", '\u03BD');
        escapeStrings.put("&xi;", '\u03BE');
        escapeStrings.put("&omicron;", '\u03BF');
        escapeStrings.put("&pi;", '\u03C0');
        escapeStrings.put("&rho;", '\u03C1');
        escapeStrings.put("&sigmaf;", '\u03C2');
        escapeStrings.put("&sigma;", '\u03C3');
        escapeStrings.put("&tau;", '\u03C4');
        escapeStrings.put("&upsilon;", '\u03C5');
        escapeStrings.put("&phi;", '\u03C6');
        escapeStrings.put("&chi;", '\u03C7');
        escapeStrings.put("&psi;", '\u03C8');
        escapeStrings.put("&omega;", '\u03C9');
        escapeStrings.put("&thetasym;", '\u03D1');
        escapeStrings.put("&upsih;", '\u03D2');
        escapeStrings.put("&piv;", '\u03D6');
        escapeStrings.put("&bull;", '\u2022');
        escapeStrings.put("&hellip;", '\u2026');
        escapeStrings.put("&prime;", '\u2032');
        escapeStrings.put("&Prime;", '\u2033');
        escapeStrings.put("&oline;", '\u203E');
        escapeStrings.put("&frasl;", '\u2044');
        escapeStrings.put("&weierp;", '\u2118');
        escapeStrings.put("&image;", '\u2111');
        escapeStrings.put("&real;", '\u211C');
        escapeStrings.put("&trade;", '\u2122');
        escapeStrings.put("&alefsym;", '\u2135');
        escapeStrings.put("&larr;", '\u2190');
        escapeStrings.put("&uarr;", '\u2191');
        escapeStrings.put("&rarr;", '\u2192');
        escapeStrings.put("&darr;", '\u2193');
        escapeStrings.put("&harr;", '\u2194');
        escapeStrings.put("&crarr;", '\u21B5');
        escapeStrings.put("&lArr;", '\u21D0');
        escapeStrings.put("&uArr;", '\u21D1');
        escapeStrings.put("&rArr;", '\u21D2');
        escapeStrings.put("&dArr;", '\u21D3');
        escapeStrings.put("&hArr;", '\u21D4');
        escapeStrings.put("&forall;", '\u2200');
        escapeStrings.put("&part;", '\u2202');
        escapeStrings.put("&exist;", '\u2203');
        escapeStrings.put("&empty;", '\u2205');
        escapeStrings.put("&nabla;", '\u2207');
        escapeStrings.put("&isin;", '\u2208');
        escapeStrings.put("&notin;", '\u2209');
        escapeStrings.put("&ni;", '\u220B');
        escapeStrings.put("&prod;", '\u220F');
        escapeStrings.put("&sum;", '\u2211');
        escapeStrings.put("&minus;", '\u2212');
        escapeStrings.put("&lowast;", '\u2217');
        escapeStrings.put("&radic;", '\u221A');
        escapeStrings.put("&prop;", '\u221D');
        escapeStrings.put("&infin;", '\u221E');
        escapeStrings.put("&ang;", '\u2220');
        escapeStrings.put("&and;", '\u2227');
        escapeStrings.put("&or;", '\u2228');
        escapeStrings.put("&cap;", '\u2229');
        escapeStrings.put("&cup;", '\u222A');
        escapeStrings.put("&int;", '\u222B');
        escapeStrings.put("&there4;", '\u2234');
        escapeStrings.put("&sim;", '\u223C');
        escapeStrings.put("&cong;", '\u2245');
        escapeStrings.put("&asymp;", '\u2248');
        escapeStrings.put("&ne;", '\u2260');
        escapeStrings.put("&equiv;", '\u2261');
        escapeStrings.put("&le;", '\u2264');
        escapeStrings.put("&ge;", '\u2265');
        escapeStrings.put("&sub;", '\u2282');
        escapeStrings.put("&sup;", '\u2283');
        escapeStrings.put("&nsub;", '\u2284');
        escapeStrings.put("&sube;", '\u2286');
        escapeStrings.put("&supe;", '\u2287');
        escapeStrings.put("&oplus;", '\u2295');
        escapeStrings.put("&otimes;", '\u2297');
        escapeStrings.put("&perp;", '\u22A5');
        escapeStrings.put("&sdot;", '\u22C5');
        escapeStrings.put("&lceil;", '\u2308');
        escapeStrings.put("&rceil;", '\u2309');
        escapeStrings.put("&lfloor;", '\u230A');
        escapeStrings.put("&rfloor;", '\u230B');
        escapeStrings.put("&lang;", '\u2329');
        escapeStrings.put("&rang;", '\u232A');
        escapeStrings.put("&loz;", '\u25CA');
        escapeStrings.put("&spades;", '\u2660');
        escapeStrings.put("&clubs;", '\u2663');
        escapeStrings.put("&hearts;", '\u2665');
        escapeStrings.put("&diams;", '\u2666');
        escapeStrings.put("&quot;", '\u0022');
        escapeStrings.put("&amp;", '\u0026');
        escapeStrings.put("&lt;", '\u003C');
        escapeStrings.put("&gt;", '\u003E');
        escapeStrings.put("&OElig;", '\u0152');
        escapeStrings.put("&oelig;", '\u0153');
        escapeStrings.put("&Scaron;", '\u0160');
        escapeStrings.put("&scaron;", '\u0161');
        escapeStrings.put("&Yuml;", '\u0178');
        escapeStrings.put("&circ;", '\u02C6');
        escapeStrings.put("&tilde;", '\u02DC');
        escapeStrings.put("&ensp;", '\u2002');
        escapeStrings.put("&emsp;", '\u2003');
        escapeStrings.put("&thinsp;", '\u2009');
        escapeStrings.put("&zwnj;", '\u200C');
        escapeStrings.put("&zwj;", '\u200D');
        escapeStrings.put("&lrm;", '\u200E');
        escapeStrings.put("&rlm;", '\u200F');
        escapeStrings.put("&ndash;", '\u2013');
        escapeStrings.put("&mdash;", '\u2014');
        escapeStrings.put("&lsquo;", '\u2018');
        escapeStrings.put("&rsquo;", '\u2019');
        escapeStrings.put("&sbquo;", '\u201A');
        escapeStrings.put("&ldquo;", '\u201C');
        escapeStrings.put("&rdquo;", '\u201D');
        escapeStrings.put("&bdquo;", '\u201E');
        escapeStrings.put("&dagger;", '\u2020');
        escapeStrings.put("&Dagger;", '\u2021');
        escapeStrings.put("&permil;", '\u2030');
        escapeStrings.put("&lsaquo;", '\u2039');
        escapeStrings.put("&rsaquo;", '\u203A');
        escapeStrings.put("&euro;", '\u20AC');
    }

    /**
     * Replace all the occurences of HTML escape strings with the
     * respective characters.
     *
     * @param s a <code>String</code> value
     * @return a <code>String</code> value
     */
    public static String unescapeHTML(String s) {
        char[] chars = s.toCharArray();
        char[] escaped = new char[chars.length];

        // Note: escaped[pos] = end of the escaped char array.
        int pos = 0;

        for (int i = 0; i < chars.length;) {
            if (chars[i] != '&') {
                escaped[pos++] = chars[i++];
                continue;
            }

            // Allow e.g. &#123;
            int j = i + 1;
            if (j < chars.length && chars[j] == '#')
                j++;

            // Scan until we find a char that is not letter or digit.
            for (; j < chars.length; j++) {
                if (!Character.isLetterOrDigit(chars[j]))
                    break;
            }

            boolean replaced = false;
            if (j < chars.length && chars[j] == ';') {
                if (s.charAt(i + 1) == '#') { // Check for &#D; and &#xD; pattern
                    try {
                        long charcode = 0;
                        char ch = s.charAt(i + 2);
                        if (ch == 'x' || ch == 'X') {
                            charcode = Long.parseLong(new String(chars, i + 3, j - i - 3),
                                    16);
                        } else if (Character.isDigit(ch)) {
                            charcode = Long.parseLong(new String(chars, i + 2, j - i - 2));
                        }
                        if (charcode > 0 && charcode < 65536) {
                            escaped[pos++] = (char) charcode;
                            replaced = true;
                        }
                    } catch (NumberFormatException ex) {
                        // Failed, not replaced.
                    }

                } else {
                    String key = new String(chars, i, j - i + 1);
                    Character repl = escapeStrings.get(key);
                    if (repl != null) {
                        escaped[pos++] = repl;
                        replaced = true;
                    }
                }
                j++;                            // Skip over ';'
            }

            if (!replaced) {
                // Not a recognized escape sequence, leave as-is
                System.arraycopy(chars, i, escaped, pos, j - i);
                pos += j - i;
            }
            i = j;
        }
        return new String(escaped, 0, pos);
    }
}