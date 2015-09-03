/* The following code was generated by JFlex 1.4.3 on 8/31/15 11:36 AM */

/* --------------------------Usercode Section------------------------ */
package parser;
import java_cup.runtime.*;

      

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 8/31/15 11:36 AM from the specification file
 * <tt>CTDS.jflex</tt>
 */
public class CTDSLexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int STRING = 2;
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\2\1\0\1\3\1\1\22\0\1\3\1\32\1\12"+
    "\2\0\1\27\1\31\1\13\1\40\1\41\1\11\1\26\1\37\1\16"+
    "\1\7\1\10\1\6\1\20\1\17\1\24\1\21\1\15\1\25\1\22"+
    "\1\23\1\5\1\0\1\36\1\34\1\33\1\35\2\0\32\4\1\44"+
    "\1\14\1\45\1\0\1\4\1\0\1\52\1\46\1\56\1\65\1\51"+
    "\1\63\1\4\1\67\1\61\1\4\1\55\1\50\1\4\1\53\1\47"+
    "\2\4\1\54\1\57\1\60\1\62\1\64\1\66\1\70\2\4\1\42"+
    "\1\30\1\43\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\2\2\1\3\2\4\1\1\1\5\1\6"+
    "\1\7\1\10\1\11\1\12\2\1\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\11\3\1\27\2\30\1\31\1\1\2\32\1\2"+
    "\1\0\1\33\1\0\1\34\1\35\1\36\1\37\1\40"+
    "\1\41\1\42\1\43\1\44\11\3\1\45\5\3\1\46"+
    "\1\47\1\50\1\51\1\52\1\47\1\53\1\54\1\55"+
    "\1\56\1\57\3\0\10\3\1\60\1\61\4\3\1\47"+
    "\2\0\2\3\1\62\4\3\1\63\2\3\1\64\1\3"+
    "\1\0\1\3\1\65\3\3\1\66\1\67\1\70\1\71"+
    "\1\0\1\3\1\72\1\73\1\3\1\0\1\74\1\3"+
    "\1\0\1\75\2\0\1\76";

  private static int [] zzUnpackAction() {
    int [] result = new int[139];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\71\0\162\0\253\0\162\0\344\0\u011d\0\u0156"+
    "\0\u018f\0\u01c8\0\162\0\162\0\u0201\0\u023a\0\162\0\u0273"+
    "\0\u02ac\0\u02e5\0\u031e\0\u0357\0\u0390\0\162\0\162\0\162"+
    "\0\162\0\162\0\162\0\162\0\162\0\u03c9\0\u0402\0\u043b"+
    "\0\u0474\0\u04ad\0\u04e6\0\u051f\0\u0558\0\u0591\0\u05ca\0\u0603"+
    "\0\162\0\162\0\u063c\0\u018f\0\u0156\0\u0675\0\u06ae\0\162"+
    "\0\u06e7\0\162\0\162\0\162\0\162\0\162\0\162\0\162"+
    "\0\162\0\162\0\u0720\0\u0759\0\u0792\0\u07cb\0\u0804\0\u083d"+
    "\0\u0876\0\u08af\0\u08e8\0\344\0\u0921\0\u095a\0\u0993\0\u09cc"+
    "\0\u0a05\0\162\0\u0a3e\0\162\0\162\0\162\0\u0a77\0\162"+
    "\0\162\0\162\0\162\0\162\0\u0ab0\0\u0ae9\0\u0b22\0\u0b5b"+
    "\0\u0b94\0\u0bcd\0\u0c06\0\u0c3f\0\u0c78\0\u0cb1\0\u0cea\0\344"+
    "\0\344\0\u0d23\0\u0d5c\0\u0d95\0\u0dce\0\162\0\u0e07\0\u0e40"+
    "\0\u0e79\0\u0eb2\0\344\0\u0eeb\0\u0f24\0\u0f5d\0\u0f96\0\344"+
    "\0\u0fcf\0\u1008\0\344\0\u1041\0\u107a\0\u10b3\0\344\0\u10ec"+
    "\0\u1125\0\u115e\0\344\0\344\0\344\0\344\0\u1197\0\u11d0"+
    "\0\344\0\344\0\u1209\0\u1242\0\344\0\u127b\0\u12b4\0\344"+
    "\0\u12ed\0\u1326\0\162";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[139];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\2\5\1\6\1\7\1\10\1\11\1\12"+
    "\1\13\1\14\2\3\1\7\1\15\7\7\1\16\1\17"+
    "\1\20\1\21\1\22\1\23\1\24\1\25\1\26\1\27"+
    "\1\30\1\31\1\32\1\33\1\34\1\35\1\36\2\6"+
    "\1\37\2\6\1\40\1\6\1\41\1\6\1\42\1\43"+
    "\1\6\1\44\1\45\1\6\1\46\2\6\1\47\1\50"+
    "\1\51\7\47\1\52\1\47\1\53\54\47\73\0\1\5"+
    "\72\0\3\6\6\0\1\6\1\0\7\6\20\0\23\6"+
    "\5\0\2\7\1\54\5\0\1\7\1\0\7\7\50\0"+
    "\2\55\1\54\5\0\1\55\1\0\7\55\50\0\2\54"+
    "\6\0\1\54\1\0\7\54\53\0\1\56\1\57\75\0"+
    "\1\60\1\61\13\0\1\62\63\0\1\63\4\0\1\64"+
    "\65\0\1\65\71\0\1\66\72\0\1\67\70\0\1\70"+
    "\70\0\1\71\70\0\1\72\41\0\3\6\6\0\1\6"+
    "\1\0\7\6\20\0\1\6\1\73\4\6\1\74\14\6"+
    "\4\0\3\6\6\0\1\6\1\0\7\6\20\0\2\6"+
    "\1\75\17\6\1\76\4\0\3\6\6\0\1\6\1\0"+
    "\7\6\20\0\3\6\1\77\17\6\4\0\3\6\6\0"+
    "\1\6\1\0\7\6\20\0\1\6\1\100\1\101\20\6"+
    "\4\0\3\6\6\0\1\6\1\0\7\6\20\0\6\6"+
    "\1\102\14\6\4\0\3\6\6\0\1\6\1\0\7\6"+
    "\20\0\5\6\1\103\7\6\1\104\5\6\4\0\3\6"+
    "\6\0\1\6\1\0\7\6\20\0\1\6\1\105\1\106"+
    "\1\6\1\107\16\6\4\0\3\6\6\0\1\6\1\0"+
    "\7\6\20\0\1\6\1\110\21\6\4\0\3\6\6\0"+
    "\1\6\1\0\7\6\20\0\21\6\1\111\1\6\1\47"+
    "\2\0\7\47\1\0\1\47\1\0\54\47\2\0\1\51"+
    "\66\0\2\112\1\0\3\112\1\113\3\112\1\114\1\115"+
    "\1\116\1\117\1\112\2\113\2\117\1\112\1\113\1\117"+
    "\20\112\1\120\4\112\1\121\1\122\3\112\1\123\2\112"+
    "\1\124\5\112\1\56\1\4\1\5\66\56\11\125\1\126"+
    "\57\125\20\0\1\127\54\0\3\6\6\0\1\6\1\0"+
    "\7\6\20\0\1\6\1\130\21\6\4\0\3\6\6\0"+
    "\1\6\1\0\7\6\20\0\3\6\1\131\17\6\4\0"+
    "\3\6\6\0\1\6\1\0\7\6\20\0\11\6\1\132"+
    "\11\6\4\0\3\6\6\0\1\6\1\0\7\6\20\0"+
    "\12\6\1\133\10\6\4\0\3\6\6\0\1\6\1\0"+
    "\7\6\20\0\12\6\1\134\10\6\4\0\3\6\6\0"+
    "\1\6\1\0\7\6\20\0\5\6\1\135\15\6\4\0"+
    "\3\6\6\0\1\6\1\0\7\6\20\0\4\6\1\136"+
    "\16\6\4\0\3\6\6\0\1\6\1\0\7\6\20\0"+
    "\14\6\1\137\6\6\4\0\3\6\6\0\1\6\1\0"+
    "\7\6\20\0\12\6\1\140\10\6\4\0\3\6\6\0"+
    "\1\6\1\0\7\6\20\0\6\6\1\141\14\6\4\0"+
    "\3\6\6\0\1\6\1\0\7\6\20\0\1\6\1\142"+
    "\21\6\4\0\3\6\6\0\1\6\1\0\7\6\20\0"+
    "\2\6\1\143\20\6\4\0\3\6\6\0\1\6\1\0"+
    "\7\6\20\0\13\6\1\144\7\6\4\0\3\6\6\0"+
    "\1\6\1\0\7\6\20\0\13\6\1\145\7\6\6\0"+
    "\1\117\6\0\1\117\1\0\4\117\1\0\2\117\51\0"+
    "\1\146\6\0\1\146\1\0\4\146\1\0\2\146\43\0"+
    "\11\125\1\147\57\125\10\0\1\5\1\126\100\0\1\150"+
    "\53\0\3\6\6\0\1\6\1\0\7\6\20\0\2\6"+
    "\1\151\20\6\4\0\3\6\6\0\1\6\1\0\7\6"+
    "\20\0\4\6\1\152\16\6\4\0\3\6\6\0\1\6"+
    "\1\0\7\6\20\0\3\6\1\153\17\6\4\0\3\6"+
    "\6\0\1\6\1\0\7\6\20\0\3\6\1\154\17\6"+
    "\4\0\3\6\6\0\1\6\1\0\7\6\20\0\14\6"+
    "\1\155\6\6\4\0\3\6\6\0\1\6\1\0\7\6"+
    "\20\0\12\6\1\156\10\6\4\0\3\6\6\0\1\6"+
    "\1\0\7\6\20\0\11\6\1\157\11\6\4\0\3\6"+
    "\6\0\1\6\1\0\7\6\20\0\3\6\1\160\17\6"+
    "\4\0\3\6\6\0\1\6\1\0\7\6\20\0\4\6"+
    "\1\161\16\6\4\0\3\6\6\0\1\6\1\0\7\6"+
    "\20\0\11\6\1\162\11\6\4\0\3\6\6\0\1\6"+
    "\1\0\7\6\20\0\17\6\1\163\3\6\4\0\3\6"+
    "\6\0\1\6\1\0\7\6\20\0\2\6\1\164\20\6"+
    "\10\125\1\5\1\147\57\125\22\0\1\165\52\0\3\6"+
    "\6\0\1\6\1\0\7\6\20\0\3\6\1\166\17\6"+
    "\4\0\3\6\6\0\1\6\1\0\7\6\20\0\7\6"+
    "\1\167\13\6\4\0\3\6\6\0\1\6\1\0\7\6"+
    "\20\0\6\6\1\170\14\6\4\0\3\6\6\0\1\6"+
    "\1\0\7\6\20\0\6\6\1\171\14\6\4\0\3\6"+
    "\6\0\1\6\1\0\7\6\20\0\13\6\1\172\7\6"+
    "\4\0\3\6\6\0\1\6\1\0\7\6\20\0\11\6"+
    "\1\173\11\6\4\0\3\6\6\0\1\6\1\0\7\6"+
    "\20\0\12\6\1\174\10\6\4\0\3\6\6\0\1\6"+
    "\1\0\7\6\20\0\3\6\1\175\17\6\4\0\3\6"+
    "\6\0\1\6\1\0\7\6\20\0\3\6\1\176\17\6"+
    "\21\0\1\177\53\0\3\6\6\0\1\6\1\0\7\6"+
    "\20\0\4\6\1\200\16\6\4\0\3\6\6\0\1\6"+
    "\1\0\7\6\20\0\5\6\1\201\15\6\4\0\3\6"+
    "\6\0\1\6\1\0\7\6\20\0\5\6\1\202\15\6"+
    "\4\0\3\6\6\0\1\6\1\0\7\6\20\0\5\6"+
    "\1\203\15\6\23\0\1\204\51\0\3\6\6\0\1\6"+
    "\1\0\7\6\20\0\5\6\1\205\15\6\4\0\3\6"+
    "\6\0\1\6\1\0\7\6\20\0\14\6\1\206\6\6"+
    "\24\0\1\207\50\0\3\6\6\0\1\6\1\0\7\6"+
    "\20\0\3\6\1\210\17\6\25\0\1\211\64\0\1\212"+
    "\72\0\1\213\45\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4959];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\1\1\1\11\5\1\2\11\2\1\1\11"+
    "\6\1\10\11\13\1\2\11\4\1\1\0\1\11\1\0"+
    "\11\11\17\1\1\11\1\1\3\11\1\1\5\11\3\0"+
    "\16\1\1\11\2\0\14\1\1\0\11\1\1\0\4\1"+
    "\1\0\2\1\1\0\1\1\2\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[139];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
	StringBuilder string = new StringBuilder();  
    /* To create a new java_cup.runtime.Symbol with information about
       the current token, the token will have no value in this
       case. */
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    /* Also creates a new java_cup.runtime.Symbol with information
       about the current token, but this object has a value. */
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public CTDSLexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public CTDSLexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 146) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 54: 
          { return symbol(sym.CLASS);
          }
        case 63: break;
        case 47: 
          { string.append( '\f' );
          }
        case 64: break;
        case 18: 
          { return symbol(sym.RPAREN);
          }
        case 65: break;
        case 14: 
          { return symbol(sym.GT);
          }
        case 66: break;
        case 43: 
          { string.append( '\b' );
          }
        case 67: break;
        case 28: 
          { return symbol(sym.MINUSEQ);
          }
        case 68: break;
        case 13: 
          { return symbol(sym.LT);
          }
        case 69: break;
        case 36: 
          { return symbol(sym.GTEQ);
          }
        case 70: break;
        case 48: 
          { return symbol(sym.INT);
          }
        case 71: break;
        case 61: 
          { return symbol(sym.CONTINUE);
          }
        case 72: break;
        case 46: 
          { string.append( '\t' );
          }
        case 73: break;
        case 21: 
          { return symbol(sym.LBRACKET);
          }
        case 74: break;
        case 11: 
          { return symbol(sym.NOT);
          }
        case 75: break;
        case 32: 
          { return symbol(sym.AND);
          }
        case 76: break;
        case 41: 
          { string.append( '\'' );
          }
        case 77: break;
        case 27: 
          { return symbol(sym.MINUSMINUS);
          }
        case 78: break;
        case 19: 
          { return symbol(sym.LKEY);
          }
        case 79: break;
        case 56: 
          { return symbol(sym.FALSE, false);
          }
        case 80: break;
        case 59: 
          { return symbol(sym.RETURN);
          }
        case 81: break;
        case 39: 
          { char val = (char) Integer.parseInt(yytext().substring(1),8);
                        				   string.append( val );
          }
        case 82: break;
        case 9: 
          { return symbol(sym.PLUS);
          }
        case 83: break;
        case 10: 
          { return symbol(sym.MOD);
          }
        case 84: break;
        case 24: 
          { throw new RuntimeException("Unterminated string at end of line");
          }
        case 85: break;
        case 20: 
          { return symbol(sym.RKEY);
          }
        case 86: break;
        case 4: 
          { return symbol(sym.INT_LITERAL, new Integer(yytext()));
          }
        case 87: break;
        case 52: 
          { return symbol(sym.VOID);
          }
        case 88: break;
        case 3: 
          { return symbol(sym.IDENTIFIER, yytext());
          }
        case 89: break;
        case 6: 
          { return symbol(sym.TIMES);
          }
        case 90: break;
        case 22: 
          { return symbol(sym.RBRACKET);
          }
        case 91: break;
        case 62: 
          { return symbol(sym.INT_LITERAL, new Integer(Integer.MIN_VALUE));
          }
        case 92: break;
        case 50: 
          { return symbol(sym.ELSE);
          }
        case 93: break;
        case 57: 
          { return symbol(sym.WHILE);
          }
        case 94: break;
        case 53: 
          { return symbol(sym.BREAK);
          }
        case 95: break;
        case 37: 
          { return symbol(sym.IF);
          }
        case 96: break;
        case 17: 
          { return symbol(sym.LPAREN);
          }
        case 97: break;
        case 30: 
          { return symbol(sym.PLUSEQ);
          }
        case 98: break;
        case 31: 
          { return symbol(sym.OR);
          }
        case 99: break;
        case 26: 
          { return symbol(sym.FLOAT_LITERAL, new Float(yytext().substring(0,yylength()-1)));
          }
        case 100: break;
        case 12: 
          { return symbol(sym.EQ);
          }
        case 101: break;
        case 40: 
          { string.append( '\"' );
          }
        case 102: break;
        case 49: 
          { return symbol(sym.FOR);
          }
        case 103: break;
        case 55: 
          { return symbol(sym.FLOAT);
          }
        case 104: break;
        case 29: 
          { return symbol(sym.PLUSPLUS);
          }
        case 105: break;
        case 25: 
          { yybegin(YYINITIAL); return symbol(sym.STRING_LITERAL, new String(string));
          }
        case 106: break;
        case 15: 
          { return symbol(sym.SEMI);
          }
        case 107: break;
        case 1: 
          { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+1+", column "+yycolumn+1);
          }
        case 108: break;
        case 8: 
          { return symbol(sym.MINUS);
          }
        case 109: break;
        case 58: 
          { return symbol(sym.EXTERN);
          }
        case 110: break;
        case 45: 
          { string.append( '\r' );
          }
        case 111: break;
        case 35: 
          { return symbol(sym.LTEQ);
          }
        case 112: break;
        case 42: 
          { string.append( '\\' );
          }
        case 113: break;
        case 7: 
          { yybegin(STRING); string.setLength(0);
          }
        case 114: break;
        case 2: 
          { /*skip*/
          }
        case 115: break;
        case 60: 
          { return symbol(sym.BOOLEAN);
          }
        case 116: break;
        case 51: 
          { return symbol(sym.TRUE, true);
          }
        case 117: break;
        case 44: 
          { string.append( '\n' );
          }
        case 118: break;
        case 5: 
          { return symbol(sym.DIVIDE);
          }
        case 119: break;
        case 33: 
          { return symbol(sym.NOTEQ);
          }
        case 120: break;
        case 16: 
          { return symbol(sym.COMMA);
          }
        case 121: break;
        case 23: 
          { string.append( yytext() );
          }
        case 122: break;
        case 38: 
          { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\"");
          }
        case 123: break;
        case 34: 
          { return symbol(sym.EQEQ);
          }
        case 124: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              {
                return symbol(sym.EOF);
              }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}