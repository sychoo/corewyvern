/*
 * Built at Wed Aug 07 12:13:17 EDT 2019
 * by Copper version 0.7.1,
 *      revision unknown,
 *      build 20140605-2206
 */
package wyvern.tools.lexing;

import edu.umn.cs.melt.copper.runtime.logging.CopperParserException;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import wyvern.tools.errors.ErrorMessage;
import wyvern.tools.errors.FileLocation;
import wyvern.tools.errors.ToolError;
import wyvern.tools.parsing.coreparser.Token;
import wyvern.tools.parsing.coreparser.WyvernParserConstants;

import static wyvern.tools.parsing.coreparser.WyvernParserConstants.*;



public class WyvernLexer extends edu.umn.cs.melt.copper.runtime.engines.single.SingleDFAEngine<List< Token >,edu.umn.cs.melt.copper.runtime.logging.CopperParserException>
{
    protected String formatError(String error)
    {
    	   String location = "";
        location += "line " + virtualLocation.getLine() + ", column " + virtualLocation.getColumn();
        if(currentState.pos.getFileName().length() > 40) location += "\n         ";
        location += " in file " + virtualLocation.getFileName();
        location += "\n         (parser state: " + currentState.statenum + "; real character index: " + currentState.pos.getPos() + ")";
        return "Error at " + location + ":\n  " + error;
    }
    protected void reportError(String message)
    throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
        throw new edu.umn.cs.melt.copper.runtime.logging.CopperParserException(message);
    }
    protected void reportSyntaxError()
    throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
    java.util.ArrayList<String> expectedTerminalsReal = bitVecToRealStringList(getShiftableSets()[currentState.statenum]);
    java.util.ArrayList<String> expectedTerminalsDisplay = bitVecToDisplayStringList(getShiftableSets()[currentState.statenum]);
    java.util.ArrayList<String> matchedTerminalsReal = bitVecToRealStringList(disjointMatch.terms);
    java.util.ArrayList<String> matchedTerminalsDisplay = bitVecToDisplayStringList(disjointMatch.terms);
    throw new edu.umn.cs.melt.copper.runtime.logging.CopperSyntaxError(virtualLocation,currentState.pos,currentState.statenum,expectedTerminalsReal,expectedTerminalsDisplay,matchedTerminalsReal,matchedTerminalsDisplay);
    }
    public static enum Terminals implements edu.umn.cs.melt.copper.runtime.engines.CopperTerminalEnum
    {
        and_t(1),
        arrow_t(2),
        asKwd_t(3),
        assertKwd_t(4),
        bar_t(5),
        booleanLit_t(6),
        booleanand_t(7),
        booleannot_t(8),
        booleanor_t(9),
        cCurly_t(10),
        cSquareBracket_t(11),
        caseKwd_t(12),
        character_t(13),
        classKwd_t(14),
        closeParen_t(15),
        colon_t(16),
        comma_t(17),
        comment_t(18),
        comprisesKwd_t(19),
        continue_line_t(20),
        dash_t(21),
        datatypeKwd_t(22),
        decimalInteger_t(23),
        defKwd_t(24),
        defaultKwd_t(25),
        divide_t(26),
        dot_t(27),
        dslLine_t(28),
        dsl_indent_t(29),
        effectKwd_t(30),
        equals_t(31),
        equalsequals_t(32),
        extendsKwd_t(33),
        floatingPoint_t(34),
        fn_newline_t(35),
        forwardKwd_t(36),
        ge_t(37),
        gt_t(38),
        identifier_t(39),
        iindent_t(40),
        importKwd_t(41),
        indent_t(42),
        inewline_t(43),
        instantiateKwd_t(44),
        le_t(45),
        liftedKwd_t(46),
        lt_t(47),
        matchKwd_t(48),
        metadataKwd_t(49),
        moduleKwd_t(50),
        mult_t(51),
        multi_comment_t(52),
        newKwd_t(53),
        newline_t(54),
        notCurly_t(55),
        notequals_t(56),
        oCurly_t(57),
        oSquareBracket_t(58),
        objtypeKwd_t(59),
        ofKwd_t(60),
        openParen_t(61),
        plus_t(62),
        pound_t(63),
        question_t(64),
        recurKwd_t(65),
        remainder_t(66),
        requireKwd_t(67),
        resourceKwd_t(68),
        shortString_t(69),
        taggedKwd_t(70),
        tarrow_t(71),
        tilde_t(72),
        toKwd_t(73),
        typeKwd_t(74),
        valKwd_t(75),
        varKwd_t(76),
        whitespace_t(77);

        private final int num;
        Terminals(int num) { this.num = num; }
        public int num() { return num; }
    }

    public void pushToken(Terminals t,String lexeme)
    {
        java.util.BitSet ts = new java.util.BitSet();
        ts.set(t.num());
        tokenBuffer.offer(new edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData(ts,currentState.pos,currentState.pos,lexeme,new java.util.LinkedList<edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData>()));
    }
    public void setupEngine()
    {
    }
    public int transition(int state,char ch)
    {
         return delta[state][cmap[ch]];
    }
    public class Semantics extends edu.umn.cs.melt.copper.runtime.engines.single.semantics.SingleDFASemanticActionContainer<edu.umn.cs.melt.copper.runtime.logging.CopperParserException>
    {

        public Semantics()
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            runInit();
        }

        public void error(edu.umn.cs.melt.copper.runtime.io.InputPosition pos,java.lang.String message)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            reportError("Error at " + pos.toString() + ":\n  " + message);
        }

        public void runDefaultTermAction()
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
        }
        public void runDefaultProdAction()
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
        }
        public void runInit()
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            
	// start with the baseline indentation level
	indents.push("");
        }
        public java.lang.Object runSemanticAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,java.lang.Object[] _children,int _prod)
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            this._pos = _pos;
            this._children = _children;
            this._prod = _prod;
            this._specialAttributes = new edu.umn.cs.melt.copper.runtime.engines.semantics.SpecialParserAttributes(virtualLocation);
            java.lang.Object RESULT = null;
            switch(_prod)
            {
            case 100:
                RESULT = runSemanticAction_100();
                break;
            case 101:
                RESULT = runSemanticAction_101();
                break;
            case 102:
                RESULT = runSemanticAction_102();
                break;
            case 103:
                RESULT = runSemanticAction_103();
                break;
            case 104:
                RESULT = runSemanticAction_104();
                break;
            case 105:
                RESULT = runSemanticAction_105();
                break;
            case 106:
                RESULT = runSemanticAction_106();
                break;
            case 107:
                RESULT = runSemanticAction_107();
                break;
            case 108:
                RESULT = runSemanticAction_108();
                break;
            case 109:
                RESULT = runSemanticAction_109();
                break;
            case 110:
                RESULT = runSemanticAction_110();
                break;
            case 111:
                RESULT = runSemanticAction_111();
                break;
            case 112:
                RESULT = runSemanticAction_112();
                break;
            case 113:
                RESULT = runSemanticAction_113();
                break;
            case 114:
                RESULT = runSemanticAction_114();
                break;
            case 115:
                RESULT = runSemanticAction_115();
                break;
            case 116:
                RESULT = runSemanticAction_116();
                break;
            case 117:
                RESULT = runSemanticAction_117();
                break;
            case 118:
                RESULT = runSemanticAction_118();
                break;
            case 119:
                RESULT = runSemanticAction_119();
                break;
            case 120:
                RESULT = runSemanticAction_120();
                break;
            case 121:
                RESULT = runSemanticAction_121();
                break;
            case 122:
                RESULT = runSemanticAction_122();
                break;
            case 123:
                RESULT = runSemanticAction_123();
                break;
            case 124:
                RESULT = runSemanticAction_124();
                break;
            case 125:
                RESULT = runSemanticAction_125();
                break;
            case 126:
                RESULT = runSemanticAction_126();
                break;
            case 127:
                RESULT = runSemanticAction_127();
                break;
            case 128:
                RESULT = runSemanticAction_128();
                break;
            case 129:
                RESULT = runSemanticAction_129();
                break;
            case 130:
                RESULT = runSemanticAction_130();
                break;
            case 131:
                RESULT = runSemanticAction_131();
                break;
            case 132:
                RESULT = runSemanticAction_132();
                break;
            case 133:
                RESULT = runSemanticAction_133();
                break;
            case 134:
                RESULT = runSemanticAction_134();
                break;
            case 135:
                RESULT = runSemanticAction_135();
                break;
            case 136:
                RESULT = runSemanticAction_136();
                break;
            case 137:
                RESULT = runSemanticAction_137();
                break;
            case 138:
                RESULT = runSemanticAction_138();
                break;
            case 139:
                RESULT = runSemanticAction_139();
                break;
            case 140:
                RESULT = runSemanticAction_140();
                break;
            case 141:
                RESULT = runSemanticAction_141();
                break;
            case 142:
                RESULT = runSemanticAction_142();
                break;
            case 143:
                RESULT = runSemanticAction_143();
                break;
            case 144:
                RESULT = runSemanticAction_144();
                break;
            case 145:
                RESULT = runSemanticAction_145();
                break;
            case 146:
                RESULT = runSemanticAction_146();
                break;
            case 147:
                RESULT = runSemanticAction_147();
                break;
            case 148:
                RESULT = runSemanticAction_148();
                break;
            case 149:
                RESULT = runSemanticAction_149();
                break;
            case 150:
                RESULT = runSemanticAction_150();
                break;
            case 151:
                RESULT = runSemanticAction_151();
                break;
            case 152:
                RESULT = runSemanticAction_152();
                break;
            case 153:
                RESULT = runSemanticAction_153();
                break;
            case 154:
                RESULT = runSemanticAction_154();
                break;
            case 155:
                RESULT = runSemanticAction_155();
                break;
            case 156:
                RESULT = runSemanticAction_156();
                break;
            case 157:
                RESULT = runSemanticAction_157();
                break;
            case 158:
                RESULT = runSemanticAction_158();
                break;
            case 159:
                RESULT = runSemanticAction_159();
                break;
            case 160:
                RESULT = runSemanticAction_160();
                break;
            case 161:
                RESULT = runSemanticAction_161();
                break;
            case 162:
                RESULT = runSemanticAction_162();
                break;
            case 163:
                RESULT = runSemanticAction_163();
                break;
            case 164:
                RESULT = runSemanticAction_164();
                break;
            case 165:
                RESULT = runSemanticAction_165();
                break;
            case 166:
                RESULT = runSemanticAction_166();
                break;
            case 167:
                RESULT = runSemanticAction_167();
                break;
            case 168:
                RESULT = runSemanticAction_168();
                break;
            case 169:
                RESULT = runSemanticAction_169();
                break;
            case 170:
                RESULT = runSemanticAction_170();
                break;
            case 171:
                RESULT = runSemanticAction_171();
                break;
            case 172:
                RESULT = runSemanticAction_172();
                break;
            case 173:
                RESULT = runSemanticAction_173();
                break;
            case 174:
                RESULT = runSemanticAction_174();
                break;
            case 175:
                RESULT = runSemanticAction_175();
                break;
            case 176:
                RESULT = runSemanticAction_176();
                break;
            case 177:
                RESULT = runSemanticAction_177();
                break;
            case 178:
                RESULT = runSemanticAction_178();
                break;
            case 179:
                RESULT = runSemanticAction_179();
                break;
            case 180:
                RESULT = runSemanticAction_180();
                break;
            case 181:
                RESULT = runSemanticAction_181();
                break;
            case 182:
                RESULT = runSemanticAction_182();
                break;
            case 183:
                RESULT = runSemanticAction_183();
                break;
            case 184:
                RESULT = runSemanticAction_184();
                break;
            case 185:
                RESULT = runSemanticAction_185();
                break;
            case 186:
                RESULT = runSemanticAction_186();
                break;
            case 187:
                RESULT = runSemanticAction_187();
                break;
            case 188:
                RESULT = runSemanticAction_188();
                break;
            case 189:
                RESULT = runSemanticAction_189();
                break;
            case 190:
                RESULT = runSemanticAction_190();
                break;
            case 191:
                RESULT = runSemanticAction_191();
                break;
            case 192:
                RESULT = runSemanticAction_192();
                break;
            case 193:
                RESULT = runSemanticAction_193();
                break;
            case 194:
                RESULT = runSemanticAction_194();
                break;
            case 195:
                RESULT = runSemanticAction_195();
                break;
            case 196:
                RESULT = runSemanticAction_196();
                break;
            case 197:
                RESULT = runSemanticAction_197();
                break;
            case 198:
                RESULT = runSemanticAction_198();
                break;
            default:
        runDefaultProdAction();
                 break;
            }
            return RESULT;
        }
        public java.lang.Object runSemanticAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData _terminal)
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            this._pos = _pos;
            this._terminal = _terminal;
            this._specialAttributes = new edu.umn.cs.melt.copper.runtime.engines.semantics.SpecialParserAttributes(virtualLocation);
            String lexeme = _terminal.lexeme;
            java.lang.Object RESULT = null;
            switch(_terminal.firstTerm)
            {
            case 1:
                RESULT = runSemanticAction_1(lexeme);
                break;
            case 2:
                RESULT = runSemanticAction_2(lexeme);
                break;
            case 3:
                RESULT = runSemanticAction_3(lexeme);
                break;
            case 4:
                RESULT = runSemanticAction_4(lexeme);
                break;
            case 5:
                RESULT = runSemanticAction_5(lexeme);
                break;
            case 6:
                RESULT = runSemanticAction_6(lexeme);
                break;
            case 7:
                RESULT = runSemanticAction_7(lexeme);
                break;
            case 8:
                RESULT = runSemanticAction_8(lexeme);
                break;
            case 9:
                RESULT = runSemanticAction_9(lexeme);
                break;
            case 10:
                RESULT = runSemanticAction_10(lexeme);
                break;
            case 11:
                RESULT = runSemanticAction_11(lexeme);
                break;
            case 13:
                RESULT = runSemanticAction_13(lexeme);
                break;
            case 14:
                RESULT = runSemanticAction_14(lexeme);
                break;
            case 15:
                RESULT = runSemanticAction_15(lexeme);
                break;
            case 16:
                RESULT = runSemanticAction_16(lexeme);
                break;
            case 17:
                RESULT = runSemanticAction_17(lexeme);
                break;
            case 18:
                RESULT = runSemanticAction_18(lexeme);
                break;
            case 19:
                RESULT = runSemanticAction_19(lexeme);
                break;
            case 20:
                RESULT = runSemanticAction_20(lexeme);
                break;
            case 21:
                RESULT = runSemanticAction_21(lexeme);
                break;
            case 22:
                RESULT = runSemanticAction_22(lexeme);
                break;
            case 23:
                RESULT = runSemanticAction_23(lexeme);
                break;
            case 24:
                RESULT = runSemanticAction_24(lexeme);
                break;
            case 25:
                RESULT = runSemanticAction_25(lexeme);
                break;
            case 26:
                RESULT = runSemanticAction_26(lexeme);
                break;
            case 27:
                RESULT = runSemanticAction_27(lexeme);
                break;
            case 28:
                RESULT = runSemanticAction_28(lexeme);
                break;
            case 29:
                RESULT = runSemanticAction_29(lexeme);
                break;
            case 30:
                RESULT = runSemanticAction_30(lexeme);
                break;
            case 31:
                RESULT = runSemanticAction_31(lexeme);
                break;
            case 32:
                RESULT = runSemanticAction_32(lexeme);
                break;
            case 33:
                RESULT = runSemanticAction_33(lexeme);
                break;
            case 34:
                RESULT = runSemanticAction_34(lexeme);
                break;
            case 35:
                RESULT = runSemanticAction_35(lexeme);
                break;
            case 36:
                RESULT = runSemanticAction_36(lexeme);
                break;
            case 37:
                RESULT = runSemanticAction_37(lexeme);
                break;
            case 38:
                RESULT = runSemanticAction_38(lexeme);
                break;
            case 39:
                RESULT = runSemanticAction_39(lexeme);
                break;
            case 40:
                RESULT = runSemanticAction_40(lexeme);
                break;
            case 41:
                RESULT = runSemanticAction_41(lexeme);
                break;
            case 42:
                RESULT = runSemanticAction_42(lexeme);
                break;
            case 43:
                RESULT = runSemanticAction_43(lexeme);
                break;
            case 44:
                RESULT = runSemanticAction_44(lexeme);
                break;
            case 45:
                RESULT = runSemanticAction_45(lexeme);
                break;
            case 46:
                RESULT = runSemanticAction_46(lexeme);
                break;
            case 47:
                RESULT = runSemanticAction_47(lexeme);
                break;
            case 48:
                RESULT = runSemanticAction_48(lexeme);
                break;
            case 49:
                RESULT = runSemanticAction_49(lexeme);
                break;
            case 50:
                RESULT = runSemanticAction_50(lexeme);
                break;
            case 51:
                RESULT = runSemanticAction_51(lexeme);
                break;
            case 52:
                RESULT = runSemanticAction_52(lexeme);
                break;
            case 53:
                RESULT = runSemanticAction_53(lexeme);
                break;
            case 54:
                RESULT = runSemanticAction_54(lexeme);
                break;
            case 55:
                RESULT = runSemanticAction_55(lexeme);
                break;
            case 56:
                RESULT = runSemanticAction_56(lexeme);
                break;
            case 57:
                RESULT = runSemanticAction_57(lexeme);
                break;
            case 58:
                RESULT = runSemanticAction_58(lexeme);
                break;
            case 61:
                RESULT = runSemanticAction_61(lexeme);
                break;
            case 62:
                RESULT = runSemanticAction_62(lexeme);
                break;
            case 63:
                RESULT = runSemanticAction_63(lexeme);
                break;
            case 64:
                RESULT = runSemanticAction_64(lexeme);
                break;
            case 65:
                RESULT = runSemanticAction_65(lexeme);
                break;
            case 66:
                RESULT = runSemanticAction_66(lexeme);
                break;
            case 67:
                RESULT = runSemanticAction_67(lexeme);
                break;
            case 68:
                RESULT = runSemanticAction_68(lexeme);
                break;
            case 69:
                RESULT = runSemanticAction_69(lexeme);
                break;
            case 70:
                RESULT = runSemanticAction_70(lexeme);
                break;
            case 71:
                RESULT = runSemanticAction_71(lexeme);
                break;
            case 72:
                RESULT = runSemanticAction_72(lexeme);
                break;
            case 73:
                RESULT = runSemanticAction_73(lexeme);
                break;
            case 74:
                RESULT = runSemanticAction_74(lexeme);
                break;
            case 75:
                RESULT = runSemanticAction_75(lexeme);
                break;
            case 76:
                RESULT = runSemanticAction_76(lexeme);
                break;
            case 77:
                RESULT = runSemanticAction_77(lexeme);
                break;
            default:
        runDefaultTermAction();
                 break;
            }
            return RESULT;
        }
        public List< Token > runSemanticAction_100()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > line = (List< Token >) _children[0];
            List< Token > RESULT = null;
             RESULT = line; 
            return RESULT;
        }
        public List< Token > runSemanticAction_101()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > line = (List< Token >) _children[0];
            List< Token > RESULT = null;
             RESULT = line; 
            return RESULT;
        }
        public List< Token > runSemanticAction_102()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token n = (Token) _children[0];
            List< Token > RESULT = null;
             RESULT = LexerUtils.makeList(n); 
            return RESULT;
        }
        public List< Token > runSemanticAction_103()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > n = (List< Token >) _children[0];
            List< Token > RESULT = null;
             RESULT = n; 
            return RESULT;
        }
        public List< Token > runSemanticAction_104()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token line = (Token) _children[1];
            List< Token > RESULT = null;
             RESULT = LexerUtils.makeList(t); RESULT.add(line); 
            return RESULT;
        }
        public List runSemanticAction_105()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token n = (Token) _children[0];
            List RESULT = null;
             RESULT = LexerUtils.makeList(n); flagTok = null; lastIndent = n; 
            return RESULT;
        }
        public List runSemanticAction_106()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            List list = (List) _children[0];
            @SuppressWarnings("unchecked") List< Token > n = (List< Token >) _children[1];
            List RESULT = null;
            
                                if (flagTok != null && flagTok.kind == EQARROW && list.size() > 0 && ((Token) list.get(list.size()-1)).kind != EQARROW && ((Token) list.get(list.size()-1)).kind != WHITESPACE) {
                                    flagTok = null;
                                }
                                list.addAll(n); RESULT = list;
                            
            return RESULT;
        }
        public List< Token > runSemanticAction_107()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > line = (List< Token >) _children[0];
            List< Token > RESULT = null;
             RESULT = line; 
            return RESULT;
        }
        public List< Token > runSemanticAction_108()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > p = (List< Token >) _children[0];
            @SuppressWarnings("unchecked") List< Token > line = (List< Token >) _children[1];
            List< Token > RESULT = null;
             p.addAll(line); RESULT = p; 
            return RESULT;
        }
        public List< Token > runSemanticAction_109()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            List list = (List) _children[0];
            Token n = (Token) _children[1];
            List< Token > RESULT = null;
            
						list.add(n);
						RESULT = LexerUtils.<WyvernParserConstants>adjustLogicalLine((LinkedList<Token>)list,
						                    virtualLocation.getFileName(), indents, WyvernParserConstants.class);
					    if (foundTilde) {
						    DSLNext = true;
						    foundTilde = false;
						}
					
            return RESULT;
        }
        public List< Token > runSemanticAction_110()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token n = (Token) _children[0];
            List< Token > RESULT = null;
             RESULT = LexerUtils.makeList(n); 
            return RESULT;
        }
        public java.lang.Object runSemanticAction_111()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            java.lang.Object idsl = (java.lang.Object) _children[1];
            java.lang.Object RESULT = null;
             RESULT = idsl; 
            return RESULT;
        }
        public java.lang.Object runSemanticAction_112()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            java.lang.Object str = (java.lang.Object) _children[0];
            java.lang.Object RESULT = null;
             RESULT = str; 
            return RESULT;
        }
        public java.lang.Object runSemanticAction_113()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            java.lang.Object str = (java.lang.Object) _children[0];
            java.lang.Object idsl = (java.lang.Object) _children[2];
            java.lang.Object stre = (java.lang.Object) _children[4];
            java.lang.Object RESULT = null;
             RESULT = str + "{" + idsl + "}" + stre; 
            return RESULT;
        }
        public java.lang.Object runSemanticAction_114()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            java.lang.Object RESULT = null;
             RESULT = ""; 
            return RESULT;
        }
        public Token runSemanticAction_115()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_116()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_117()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_118()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_119()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_120()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_121()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_122()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_123()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_124()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_125()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_126()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_127()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_128()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_129()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_130()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_131()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_132()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_133()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_134()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_135()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_136()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_137()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_138()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_139()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public List runSemanticAction_140()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token n = (Token) _children[0];
            List RESULT = null;
             RESULT = LexerUtils.makeList(n); flagTok = null; lastIndent = n; 
            return RESULT;
        }
        public List runSemanticAction_141()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > n = (List< Token >) _children[0];
            List RESULT = null;
            
	                            lastIndent = null;
	                            // handles lines that start without any indent
	                            if (inDSL)
	                                inDSL = false;
								if (DSLNext)
									throw new CopperParserException("Indicated DSL with ~ but then did not indent");
	                      		RESULT = n;
	                        
            return RESULT;
        }
        public List runSemanticAction_142()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            List list = (List) _children[0];
            @SuppressWarnings("unchecked") List< Token > n = (List< Token >) _children[1];
            List RESULT = null;
            
                                if (flagTok != null && flagTok.kind == EQARROW && list.size() > 0 && ((Token) list.get(list.size()-1)).kind != EQARROW && ((Token) list.get(list.size()-1)).kind != WHITESPACE) {
                                    flagTok = null;
                                }
                                list.addAll(n); RESULT = list;
                            
            return RESULT;
        }
        public List< Token > runSemanticAction_143()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > line = (List< Token >) _children[0];
            List< Token > RESULT = null;
             RESULT = line; 
            return RESULT;
        }
        public List< Token > runSemanticAction_144()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > p = (List< Token >) _children[0];
            @SuppressWarnings("unchecked") List< Token > line = (List< Token >) _children[1];
            List< Token > RESULT = null;
             p.addAll(line); RESULT = p; 
            return RESULT;
        }
        public Token runSemanticAction_145()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_146()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_147()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_148()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_149()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_150()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            java.lang.Object lit = (java.lang.Object) _children[0];
            Token RESULT = null;
             RESULT = token(DSL_LITERAL,(String)lit); 
            return RESULT;
        }
        public List< Token > runSemanticAction_151()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            List list = (List) _children[0];
            Token n = (Token) _children[1];
            List< Token > RESULT = null;
            
						list.add(n);
						RESULT = LexerUtils.<WyvernParserConstants>adjustLogicalLine((LinkedList<Token>)list,
						                    virtualLocation.getFileName(), indents, WyvernParserConstants.class);
					    if (foundTilde) {
						    DSLNext = true;
						    foundTilde = false;
						}
					
            return RESULT;
        }
        public List< Token > runSemanticAction_152()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token n = (Token) _children[0];
            List< Token > RESULT = null;
             RESULT = LexerUtils.makeList(n); 
            return RESULT;
        }
        public List< Token > runSemanticAction_153()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token n = (Token) _children[0];
            List< Token > RESULT = null;
             RESULT = LexerUtils.makeList(n); 
            return RESULT;
        }
        public List< Token > runSemanticAction_154()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token n = (Token) _children[0];
            List< Token > RESULT = null;
             RESULT = LexerUtils.makeList(n); 
            return RESULT;
        }
        public List< Token > runSemanticAction_155()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token n = (Token) _children[0];
            List< Token > RESULT = null;
             RESULT = LexerUtils.makeList(n); 
            return RESULT;
        }
        public List< Token > runSemanticAction_156()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            List< Token > RESULT = null;
             RESULT = LexerUtils.makeList(t); 
            return RESULT;
        }
        public List< Token > runSemanticAction_157()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            List< Token > RESULT = null;
             RESULT = LexerUtils.makeList(t); 
            return RESULT;
        }
        public List< Token > runSemanticAction_158()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            List< Token > RESULT = null;
             RESULT = LexerUtils.makeList(t); 
            return RESULT;
        }
        public List< Token > runSemanticAction_159()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            List< Token > RESULT = null;
             RESULT = LexerUtils.makeList(t); 
            return RESULT;
        }
        public List< Token > runSemanticAction_160()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > l = (List< Token >) _children[0];
            List< Token > RESULT = null;
             RESULT = l; 
            return RESULT;
        }
        public Token runSemanticAction_161()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             foundTilde = true; RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_162()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_163()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_164()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_165()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_166()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_167()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_168()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_169()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_170()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_171()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_172()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_173()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_174()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_175()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_176()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_177()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_178()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_179()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_180()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_181()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_182()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_183()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_184()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public Token runSemanticAction_185()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            Token RESULT = null;
             RESULT = t; 
            return RESULT;
        }
        public List< Token > runSemanticAction_186()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > ps = (List< Token >) _children[0];
            List< Token > RESULT = null;
             RESULT = ps; 
            return RESULT;
        }
        public List< Token > runSemanticAction_187()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            List< Token > RESULT = null;
             RESULT = LexerUtils.emptyList(); 
            return RESULT;
        }
        public List< Token > runSemanticAction_188()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > e = (List< Token >) _children[0];
            List< Token > RESULT = null;
            
                        adjustEQARROW(e);
                        RESULT = e;
                    
            return RESULT;
        }
        public List< Token > runSemanticAction_189()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            List< Token > RESULT = null;
             RESULT = LexerUtils.makeList(t); 
            return RESULT;
        }
        public List< Token > runSemanticAction_190()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t = (Token) _children[0];
            @SuppressWarnings("unchecked") List< Token > l = (List< Token >) _children[1];
            List< Token > RESULT = null;
            
                        RESULT = LexerUtils.makeList(t);
                        RESULT.addAll(l);
                        lambdas.pop();
                        // pop the old stack back on
                        indents = metaStack.pop();
                        RESULT.add(LexerUtils.makeToken(WyvernParserConstants.DEDENT, "DEDENT_end_of_lambda", t));
                    
            return RESULT;
        }
        public List< Token > runSemanticAction_191()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > p = (List< Token >) _children[0];
            List< Token > RESULT = null;
             RESULT = p; 
            return RESULT;
        }
        public List< Token > runSemanticAction_192()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > ps = (List< Token >) _children[0];
            @SuppressWarnings("unchecked") List< Token > p = (List< Token >) _children[1];
            List< Token > RESULT = null;
             RESULT = ps; ps.addAll(p); 
            return RESULT;
        }
        public List< Token > runSemanticAction_193()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t1 = (Token) _children[0];
            @SuppressWarnings("unchecked") List< Token > list = (List< Token >) _children[1];
            Token t2 = (Token) _children[2];
            List< Token > RESULT = null;
            
	               RESULT = LexerUtils.makeList(t1);
	               RESULT.addAll(list);
	               RESULT.add(t2);
	           
            return RESULT;
        }
        public List< Token > runSemanticAction_194()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token t1 = (Token) _children[0];
            @SuppressWarnings("unchecked") List< Token > list = (List< Token >) _children[1];
            Token t2 = (Token) _children[2];
            List< Token > RESULT = null;
            
	               RESULT = LexerUtils.makeList(t1);
	               RESULT.addAll(list);
	               RESULT.add(t2);
	           
            return RESULT;
        }
        public List< Token > runSemanticAction_195()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > p = (List< Token >) _children[0];
            List< Token > RESULT = null;
            
	             	RESULT = p;
	             	Token t = ((LinkedList<Token>)p).getLast();
	             	RESULT.addAll(LexerUtils.<WyvernParserConstants>possibleDedentList(
	             	    t, virtualLocation.getFileName(), indents, WyvernParserConstants.class));
	           	
            return RESULT;
        }
        public List< Token > runSemanticAction_196()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unchecked") List< Token > p = (List< Token >) _children[0];
            List list = (List) _children[1];
            List< Token > RESULT = null;
            
	          		// handle the case of ending in an incomplete line
	          		RESULT = p;
	          		List<Token> adjustedList = LexerUtils.<WyvernParserConstants>adjustLogicalLine(
	          		    (LinkedList<Token>)list, virtualLocation.getFileName(), indents, WyvernParserConstants.class);
	          		RESULT.addAll(adjustedList);
	             	Token t = ((LinkedList<Token>)adjustedList).getLast();
	          		RESULT.addAll(LexerUtils.<WyvernParserConstants>possibleDedentList(
	          		    t, virtualLocation.getFileName(), indents, WyvernParserConstants.class));
	          	
            return RESULT;
        }
        public List< Token > runSemanticAction_197()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            List list = (List) _children[0];
            List< Token > RESULT = null;
             RESULT = list; 
            return RESULT;
        }
        public List< Token > runSemanticAction_198()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            List< Token > RESULT = null;
             RESULT = LexerUtils.emptyList(); 
            return RESULT;
        }
        public Token runSemanticAction_1(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(AND,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_2(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
            
        RESULT = token(EQARROW,lexeme);
        if (flagTok == null) {
            flagTok = RESULT;
        }
    
            return RESULT;
        }
        public Token runSemanticAction_3(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(AS,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_4(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(ASSERT,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_5(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(BAR,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_6(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(BOOLEAN_LITERAL,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_7(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(BOOLEANAND,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_8(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(BOOLEANNOT,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_9(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(BOOLEANOR,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_10(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(RBRACE,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_11(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(RBRACK,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_13(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
            
 		RESULT = token(CHARACTER_LITERAL, replaceEscapeSequences(lexeme.substring(2,lexeme.length()-1)));
 	
            return RESULT;
        }
        public Token runSemanticAction_14(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(CLASS,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_15(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(RPAREN,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_16(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(COLON,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_17(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(COMMA,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_18(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(SINGLE_LINE_COMMENT,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_19(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(COMPRISES,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_20(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(WHITESPACE,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_21(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(DASH,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_22(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(DATATYPE, lexeme); flagTok = RESULT; 
            return RESULT;
        }
        public Token runSemanticAction_23(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(DECIMAL_LITERAL,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_24(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(DEF,lexeme); flagTok = RESULT; 
            return RESULT;
        }
        public Token runSemanticAction_25(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(DEFLT,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_26(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(DIVIDE,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_27(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(DOT,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_28(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(DSLLINE,lexeme); flagTok = null; 
            return RESULT;
        }
        public Token runSemanticAction_29(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(WHITESPACE,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_30(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(EFFECT,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_31(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
            
		RESULT = token(EQUALS,lexeme);
		if (flagTok != null && flagTok.kind == DEF) // EQUALS cancels a DEF for the purposes of whether we are looking for a DSL on the next line
			flagTok = null;
	
            return RESULT;
        }
        public Token runSemanticAction_32(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(EQUALSEQUALS,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_33(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(EXTENDS,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_34(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(FLOATING_POINT_LITERAL, lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_35(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
            
        ilineNext = true;
        RESULT = token(WHITESPACE,lexeme);
        // save a copy of the indent Stack, adjust the stack to the current indent level
        metaStack.push((Stack<String>) indents.clone());
        String thisIndent = "";
        if (lastIndent != null) {
            thisIndent = lastIndent.image;
        }
        LexerUtils.adjustIndent(thisIndent, RESULT, virtualLocation.getFileName(), indents);
    
            return RESULT;
        }
        public Token runSemanticAction_36(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(FORWARD,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_37(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(GE,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_38(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(GT,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_39(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
            
 		RESULT = token(IDENTIFIER,lexeme);
 	
            return RESULT;
        }
        public Token runSemanticAction_40(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
            
        RESULT = token(WHITESPACE,lexeme);
        if (ilineNext) {
            lambdas.push(lexeme);
            ilineNext = false;
        }
    
            return RESULT;
        }
        public Token runSemanticAction_41(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(IMPORT,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_42(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(WHITESPACE,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_43(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(WHITESPACE,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_44(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(INSTANTIATE,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_45(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(LE,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_46(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(LIFTED,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_47(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(LT,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_48(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(MATCH,lexeme); flagTok = RESULT; 
            return RESULT;
        }
        public Token runSemanticAction_49(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(METADATA,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_50(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(MODULE,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_51(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(MULT,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_52(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(MULTI_LINE_COMMENT,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_53(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(NEW,lexeme); flagTok = RESULT; 
            return RESULT;
        }
        public Token runSemanticAction_54(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(WHITESPACE,lexeme); 
            return RESULT;
        }
        public java.lang.Object runSemanticAction_55(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            java.lang.Object RESULT = null;
             RESULT = lexeme; 
            return RESULT;
        }
        public Token runSemanticAction_56(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(NOTEQUALS,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_57(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(LBRACE,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_58(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(LBRACK,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_61(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(LPAREN,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_62(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(PLUS,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_63(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(POUND,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_64(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(QUESTION,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_65(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(RECUR,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_66(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(MOD,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_67(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(REQUIRE,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_68(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(RESOURCE,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_69(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
            
 		RESULT = token(STRING_LITERAL, replaceEscapeSequences(lexeme.substring(1,lexeme.length()-1)));
 	
            return RESULT;
        }
        public Token runSemanticAction_70(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(TAGGED,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_71(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(TARROW,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_72(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(TILDE,lexeme); flagTok = RESULT; 
            return RESULT;
        }
        public Token runSemanticAction_73(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(TO,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_74(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(TYPE,lexeme); flagTok = RESULT; 
            return RESULT;
        }
        public Token runSemanticAction_75(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(VAL,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_76(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(VAR,lexeme); 
            return RESULT;
        }
        public Token runSemanticAction_77(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            Token RESULT = null;
             RESULT = token(WHITESPACE,lexeme); 
            return RESULT;
        }
        public int runDisambiguationAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData match)
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            String lexeme = match.lexeme;
            if(match.terms.equals(disambiguationGroups[0])) return disambiguate_0(lexeme);
            else if(match.terms.equals(disambiguationGroups[1])) return disambiguate_1(lexeme);
            else if(match.terms.equals(disambiguationGroups[2])) return disambiguate_2(lexeme);
            else if(match.terms.equals(disambiguationGroups[3])) return disambiguate_3(lexeme);
            else return -1;
        }
        public int disambiguate_0(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unused") final int dsl_indent_t = 29;
            @SuppressWarnings("unused") final int indent_t = 42;
            
		String currentIndent = indents.peek();
		if (lastIndent != null && !(currentIndent.equals(lastIndent.image)))
		    currentIndent = lastIndent.image;
		if (lexeme.length() > currentIndent.length() && lexeme.startsWith(currentIndent)) {
			// indented
			/*if (DSLNext != isDSLNext()) {
			    //throw new RuntimeException("unexpected difference");
			}*/
			if (/*DSLNext*/ isDSLNext() || inDSL) {
				DSLNext = false;
				inDSL = true;
				return dsl_indent_t;
			} else {
				return indent_t;
			}
		}
		if (DSLNext)
			throw new CopperParserException("Indicated DSL with ~ but then did not indent");
		inDSL = false;
		return indent_t;
	
        }
        public int disambiguate_1(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unused") final int fn_newline_t = 35;
            @SuppressWarnings("unused") final int newline_t = 54;
            
        if (isEQARROWlast) {
            return fn_newline_t;
        } else {
            return newline_t;
        }
	
        }
        public int disambiguate_2(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unused") final int fn_newline_t = 35;
            @SuppressWarnings("unused") final int inewline_t = 43;
            @SuppressWarnings("unused") final int newline_t = 54;
            
        if (isEQARROWlast) {
            return fn_newline_t;
        } else if (lambdas.size()>0) {
            return inewline_t;
        } else {
            return newline_t;
        }
	
        }
        public int disambiguate_3(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unused") final int iindent_t = 40;
            @SuppressWarnings("unused") final int whitespace_t = 77;
            
        if (ilineNext) {
            return iindent_t;
        } else {
            if (lambdas.size() == 0) {
                return whitespace_t;
            }
            String lambdaIndent = lambdas.get(lambdas.size()-1);
            if (lexeme.length() >= lambdaIndent.length() && lexeme.startsWith(lambdaIndent)) {
                return iindent_t;
            } else {
                return whitespace_t;
            }
        }
    
        }
    }
    public Semantics semantics;
    public java.lang.Object runSemanticAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,java.lang.Object[] _children,int _prod)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
        return semantics.runSemanticAction(_pos,_children,_prod);
    }
    public java.lang.Object runSemanticAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData _terminal)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
        return semantics.runSemanticAction(_pos,_terminal);
    }
    public int runDisambiguationAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData matches)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
        return semantics.runDisambiguationAction(_pos,matches);
    }
    public edu.umn.cs.melt.copper.runtime.engines.semantics.SpecialParserAttributes getSpecialAttributes()
    {
        return semantics.getSpecialAttributes();
    }
    public void startEngine(edu.umn.cs.melt.copper.runtime.io.InputPosition initialPos)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
         super.startEngine(initialPos);
         semantics = new Semantics();
    }

public static final byte[] symbolNamesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\155\223\315\216\333\066" +
"\020\307\205\046\266\345\217\155\026\110\257\175\205\040\372\264" +
"\215\236\322\040\055\212\056\320\105\035\264\207\036\014\056\105" +
"\313\114\050\122\241\250\165\026\175\245\366\071\162\317\113\004" +
"\071\364\035\112\362\117\331\012\332\013\071\044\147\176\363\347" +
"\160\370\327\077\321\244\327\321\323\077\156\336\220\173\362\114" +
"\020\131\077\333\031\315\145\375\335\337\037\177\373\364\371\333" +
"\077\177\374\052\212\336\267\121\024\175\060\321\243\127\277\374" +
"\140\242\011\221\325\336\230\150\106\264\126\047\130\335\317\047" +
"\277\267\044\135\307\264\011\253\311\035\321\156\136\335\051\045" +
"\030\221\067\334\214\227\201\063\054\245\362\247\313\260\124\076" +
"\064\246\057\173\055\036\234\171\115\167\357\172\242\331\367\232" +
"\320\267\314\373\316\051\351\330\220\232\036\211\075\061\314\307" +
"\055\250\260\122\302\321\212\012\325\261\133\033\053\275\134\252" +
"\204\032\254\246\041\040\131\213\111\117\375\332\332\255\346\035" +
"\033\342\237\120\045\015\227\075\333\013\056\231\333\231\126\244" +
"\073\072\343\252\042\206\230\207\166\120\161\135\061\312\033\042" +
"\176\222\206\325\220\022\127\354\060\010\261\046\351\305\120\237" +
"\270\342\367\274\362\300\111\205\333\317\253\116\334\204\044\053" +
"\153\357\271\254\202\254\045\073\034\030\075\307\062\133\014\321" +
"\171\275\060\057\033\053\366\336\060\131\235\345\037\204\042\126" +
"\177\175\253\070\120\253\203\334\113\166\032\156\263\072\050\175" +
"\042\272\012\376\217\153\206\011\276\334\011\340\007\216\333\314" +
"\371\110\021\157\132\165\176\355\370\162\260\340\043\372\065\227" +
"\235\041\026\101\314\120\245\307\302\237\054\005\077\030\166\116" +
"\053\020\333\020\103\217\141\357\252\141\206\270\022\017\217\334" +
"\250\252\027\003\146\332\364\210\171\342\014\276\037\275\141\154" +
"\005\004\257\371\110\313\302\066\331\271\241\226\166\161\051\132" +
"\254\056\235\246\376\323\151\053\165\367\146\364\314\063\065\074" +
"\351\122\265\114\236\133\153\332\212\336\323\146\255\352\321\336" +
"\213\167\075\353\014\107\303\055\064\243\275\036\102\065\153\210" +
"\053\032\076\211\266\132\270\036\062\134\151\326\251\136\323\363" +
"\272\073\332\122\343\157\372\140\103\352\372\134\273\330\134\076" +
"\243\341\002\075\065\063\152\050\301\110\172\174\117\304\305\034" +
"\264\254\116\107\156\130\327\022\212\166\334\275\176\361\353\153" +
"\367\327\135\063\332\026\043\362\301\131\257\004\163\025\266\354" +
"\320\247\046\372\206\213\313\301\316\336\201\111\152\267\247\176" +
"\273\163\375\043\124\315\051\011\356\163\056\335\201\340\350\030" +
"\311\264\045\331\307\177\313\036\116\046\172\372\277\254\111\100" +
"\315\154\024\323\104\270\316\031\063\257\245\222\277\357\276\320" +
"\027\333\147\321\304\330\222\331\327\154\215\177\240\227\366\027" +
"\333\103\247\251\035\255\155\155\333\057\217\247\176\355\022\266" +
"\132\325\232\064\166\313\027\344\326\012\335\267\233\002\123\351" +
"\247\022\253\022\253\165\070\333\372\151\373\034\123\206\051\307" +
"\224\140\112\021\020\342\326\230\066\230\020\236\040\074\101\100" +
"\202\200\004\260\004\260\004\371\022\120\022\120\022\120\022\120" +
"\122\120\122\120\122\120\122\120\122\120\122\120\122\120\122\120" +
"\122\120\122\120\062\120\062\120\062\120\062\113\171\144\257\202" +
"\073\303\143\003\217\015\074\066\140\155\300\312\220\056\103\272" +
"\014\351\062\270\144\301\045\300\240\157\203\200\022\056\045\134" +
"\112\270\254\221\157\215\174\353\120\116\304\255\021\227\303\045" +
"\207\113\016\227\034\056\171\160\201\226\034\132\162\044\312\221" +
"\050\107\242\002\224\002\224\002\224\002\224\002\224\002\224\002" +
"\224\002\224\002\224\002\224\022\224\022\224\022\224\022\224\062" +
"\367\165\054\374\130\372\361\271\037\023\077\246\176\104\255\341" +
"\271\366\043\370\133\344\336\042\367\026\271\267\233\177\001\371" +
"\131\332\334\333\007\000\000"
});

public static final byte[] symbolDisplayNamesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\155\223\315\216\333\066" +
"\020\307\225\164\155\313\037\333\054\220\136\163\353\071\210\076" +
"\155\043\267\006\101\121\144\017\001\026\110\017\005\142\160\051" +
"\132\146\102\221\132\212\132\147\321\127\112\237\243\367\276\104" +
"\221\103\336\041\044\377\224\255\240\275\160\206\344\314\157\376" +
"\032\216\076\177\215\046\275\216\236\376\161\375\201\334\223\347" +
"\202\310\372\371\215\321\134\326\057\377\372\347\335\277\137\236" +
"\375\371\353\343\050\372\324\106\121\364\267\211\036\375\154\242" +
"\011\221\325\316\230\150\106\264\126\107\170\335\233\243\077\133" +
"\222\256\143\332\204\335\344\226\150\147\127\267\112\011\106\344" +
"\065\067\343\155\340\014\133\251\374\355\062\154\225\117\215\351" +
"\253\136\213\007\347\136\321\233\273\236\150\366\213\046\364\043" +
"\363\261\163\112\072\066\224\246\007\142\157\014\363\171\013\052" +
"\254\224\160\265\242\102\165\354\255\315\225\136\056\125\102\015" +
"\136\323\020\220\254\307\244\247\376\150\375\126\363\216\015\371" +
"\117\250\222\206\313\236\355\004\227\314\235\114\053\322\035\234" +
"\163\131\021\103\314\103\073\250\270\252\030\345\015\021\277\111" +
"\303\152\110\211\053\266\037\204\130\227\364\142\350\117\134\361" +
"\173\136\171\340\244\302\327\317\253\116\134\207\042\053\353\357" +
"\270\254\202\254\045\333\357\031\075\345\062\333\014\321\171\275" +
"\160\317\007\053\366\311\060\131\235\344\357\205\042\126\177\375" +
"\126\161\240\126\173\271\223\354\070\174\315\152\257\364\221\350" +
"\052\304\137\324\014\006\261\334\011\340\173\216\257\231\363\221" +
"\042\336\264\352\364\332\361\371\142\301\107\364\053\056\073\103" +
"\054\202\230\241\113\027\302\337\054\005\337\033\166\052\053\220" +
"\333\020\103\017\341\354\262\141\206\270\026\017\217\334\250\252" +
"\027\003\146\332\364\310\171\342\034\276\033\275\141\154\005\204" +
"\250\371\110\313\302\016\331\151\240\226\166\163\156\132\254\316" +
"\223\246\376\063\151\053\165\373\141\364\314\063\065\074\351\122" +
"\265\114\236\106\153\332\212\336\323\146\255\352\061\336\213\273" +
"\236\165\206\143\340\026\232\321\136\017\251\232\065\304\065\015" +
"\077\211\266\132\270\036\052\134\152\326\251\136\323\323\276\073" +
"\330\126\343\317\364\311\206\324\365\251\167\261\071\377\214\206" +
"\013\314\324\314\250\241\005\043\351\361\075\021\147\167\320\262" +
"\072\036\270\141\135\113\250\117\175\364\336\375\347\156\020\355" +
"\170\021\371\340\274\327\202\271\356\132\156\230\121\023\375\304" +
"\305\371\342\306\352\147\222\332\343\251\077\356\334\354\010\125" +
"\163\112\102\370\234\113\167\041\070\246\105\062\155\111\366\341" +
"\077\262\207\243\211\236\376\057\153\022\120\063\233\305\064\021" +
"\156\152\306\314\053\251\344\357\067\337\351\213\355\223\150\142" +
"\154\273\354\113\266\306\077\316\053\373\007\333\113\247\251\035" +
"\355\155\137\333\357\257\247\176\357\012\266\132\325\232\064\046" +
"\172\374\336\166\343\142\327\156\012\230\322\233\022\273\022\273" +
"\165\270\333\172\263\175\001\223\301\344\060\011\114\212\204\220" +
"\267\206\331\300\040\075\101\172\202\204\004\011\011\140\011\140" +
"\011\352\045\240\044\240\044\240\044\240\244\240\244\240\244\240" +
"\244\240\244\240\244\240\244\240\244\240\244\240\244\240\144\240" +
"\144\240\144\240\144\226\362\203\375\024\174\063\042\066\210\330" +
"\040\142\003\326\006\254\014\345\062\224\313\120\056\103\110\026" +
"\102\002\014\372\066\110\050\021\122\042\244\104\310\032\365\326" +
"\250\267\016\355\104\336\032\171\071\102\162\204\344\010\311\021" +
"\222\207\020\150\311\241\045\107\241\034\205\162\024\052\100\051" +
"\100\051\100\051\100\051\100\051\100\051\100\051\100\051\100\051" +
"\100\051\101\051\101\051\101\051\101\051\163\337\307\302\257\245" +
"\137\137\370\065\361\153\352\127\364\032\221\153\277\202\277\105" +
"\355\055\152\157\121\173\273\371\006\023\267\113\143\321\007\000" +
"\000"
});

public static final byte[] symbolNumbersHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\216\063\214\002\262\200\002\015\260\003\003" +
"\003\023\020\063\242\141\046\054\064\214\315\014\245\131\035\040" +
"\372\321\365\322\022\243\273\013\237\232\301\202\221\303\010\331" +
"\375\314\110\141\011\023\147\000\000\305\146\363\370\067\003\000" +
"\000"
});

public static final byte[] productionLHSsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\122\024\030\030\374\200\330\037\212\003\240" +
"\070\020\210\203\240\070\030\212\103\240\070\024\210\303\220\160" +
"\070\035\160\004\022\216\204\342\050\034\070\032\212\143\010\340" +
"\130\072\340\070\050\216\107\302\011\120\234\010\305\111\310\030" +
"\000\100\230\261\250\253\001\000\000"
});

public static final byte[] parseTableHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\235\347\263\144\145" +
"\021\306\117\055\142\304\214\042\230\216\212\050\146\105\301\200" +
"\172\115\053\013\042\346\264\206\205\005\044\047\135\304\204\071" +
"\140\102\061\140\316\030\061\141\136\163\374\113\374\144\225\237" +
"\375\152\137\351\176\171\273\147\346\314\323\075\075\175\101\247" +
"\253\176\325\263\147\336\347\074\375\114\327\335\275\165\303\354" +
"\365\377\032\016\075\160\331\160\310\336\275\173\216\370\367\077" +
"\116\073\362\352\253\116\334\065\014\127\134\062\014\303\305\164" +
"\175\327\336\075\247\036\334\167\314\345\377\274\341\072\271\274" +
"\177\330\324\315\246\016\134\072\134\071\320\146\206\375\133\303" +
"\360\367\161\030\166\021\207\020\267\042\016\045\156\115\334\206" +
"\270\055\161\073\342\366\275\232\376\174\007\342\060\176\174\107" +
"\342\116\304\235\211\273\020\167\045\356\106\334\235\070\234\270" +
"\007\161\117\342\010\342\136\335\075\216\044\216\042\356\115\334" +
"\207\270\057\137\277\037\161\177\142\044\036\300\327\036\110\074" +
"\210\037\037\115\074\230\070\206\170\010\361\120\342\130\342\141" +
"\304\303\211\107\020\217\044\036\305\347\037\115\074\206\170\154" +
"\347\375\070\342\070\342\361\304\023\210\343\211\023\210\047\022" +
"\117\042\236\114\074\205\070\221\170\052\361\064\342\351\304\026" +
"\361\214\145\257\056\235\171\046\367\147\021\317\046\236\103\354" +
"\046\236\113\234\104\354\061\347\117\046\116\121\133\371\011\302" +
"\266\272\357\121\144\222\012\137\253\361\172\043\071\062\113\155" +
"\345\173\010\234\246\365\050\062\101\205\257\325\170\275\221\034" +
"\231\245\266\362\001\004\116\323\172\024\231\240\302\327\152\274" +
"\336\110\216\314\122\133\271\002\201\323\264\036\105\046\250\360" +
"\265\032\257\067\222\043\263\324\126\176\214\300\151\132\217\042" +
"\023\124\370\132\215\327\033\311\221\131\152\053\237\104\340\064" +
"\255\107\221\011\052\174\255\306\353\215\344\310\054\265\225\137" +
"\042\160\232\326\243\310\004\025\276\126\343\365\106\162\144\226" +
"\332\312\257\021\070\115\353\121\144\202\012\137\253\361\172\043" +
"\071\062\113\155\345\127\010\234\246\365\050\062\101\205\257\325" +
"\170\275\221\034\231\245\266\362\151\004\116\323\172\024\231\240" +
"\302\327\152\274\336\110\216\314\122\133\371\060\002\247\151\075" +
"\212\114\120\341\153\065\136\157\044\107\146\251\255\374\000\201" +
"\323\264\036\105\046\250\360\265\032\257\067\222\043\263\324\126" +
"\276\213\300\151\132\217\042\023\124\370\132\215\327\033\311\221" +
"\131\152\053\237\107\340\064\255\107\221\011\052\174\255\306\353" +
"\215\344\310\054\265\225\167\040\160\232\326\243\310\004\025\276" +
"\126\343\365\106\162\144\226\332\312\027\021\070\115\353\121\144" +
"\202\012\137\253\361\172\043\071\062\113\155\345\033\010\234\246" +
"\365\050\062\101\205\257\325\170\275\221\034\231\245\266\362\106" +
"\004\116\323\172\024\231\240\302\327\152\274\336\110\216\314\122" +
"\133\371\004\002\247\151\075\212\114\120\341\153\065\136\157\044" +
"\107\146\251\255\134\216\300\151\132\217\042\023\124\370\132\215" +
"\327\033\311\221\131\152\053\357\106\340\064\255\107\221\011\052" +
"\174\255\306\353\215\344\310\054\265\225\353\020\070\115\353\121" +
"\144\202\012\137\253\361\172\043\071\062\113\155\345\172\004\116" +
"\323\172\024\231\240\302\327\152\274\336\110\216\314\122\133\271" +
"\012\201\323\264\036\105\046\250\360\265\032\257\067\222\043\263" +
"\324\126\276\215\300\151\132\217\042\023\124\370\132\215\327\033" +
"\311\221\131\152\053\337\101\340\064\255\107\221\011\052\174\255" +
"\306\353\215\344\310\054\265\225\167\042\160\232\326\243\310\004" +
"\025\276\126\343\365\106\162\144\226\332\312\065\010\234\246\365" +
"\050\062\101\205\257\325\170\275\221\034\231\245\266\362\146\004" +
"\116\323\172\024\231\240\302\327\152\274\336\110\216\314\122\133" +
"\371\031\002\247\151\075\212\114\120\341\153\065\136\157\044\107" +
"\146\251\255\334\200\300\151\132\217\042\023\124\370\132\215\327" +
"\033\311\221\131\152\053\237\103\340\064\255\107\221\011\052\174" +
"\255\306\353\215\344\310\054\265\225\367\042\160\232\326\243\310" +
"\004\025\276\126\343\365\106\162\144\226\332\312\107\020\172\165" +
"\177\015\325\333\373\054\172\156\321\265\051\335\224\027\352\035" +
"\315\221\131\152\053\357\107\340\064\255\107\221\011\052\174\255" +
"\306\353\215\344\310\054\265\225\237\043\160\232\326\243\310\004" +
"\025\276\126\343\365\106\162\144\226\332\312\373\020\070\115\353" +
"\121\144\202\012\137\253\361\172\043\071\062\113\155\345\247\010" +
"\234\246\365\050\062\101\205\257\325\170\275\221\034\231\245\266" +
"\362\056\004\116\323\172\024\231\240\302\327\152\274\336\110\216" +
"\314\122\133\171\033\002\247\151\075\212\114\120\341\153\065\136" +
"\157\044\107\146\251\255\134\211\300\151\132\217\042\023\124\370" +
"\132\215\327\033\311\221\131\152\053\337\104\340\064\255\107\221" +
"\011\052\174\255\306\353\215\344\310\054\265\225\057\040\160\232" +
"\326\243\310\004\025\276\126\343\365\106\162\144\226\332\312\333" +
"\021\070\115\353\121\144\202\012\137\253\361\172\043\071\062\113" +
"\155\345\263\010\275\272\277\206\352\021\315\274\347\126\361\263" +
"\347\355\374\121\200\027\070\124\152\053\277\100\340\064\255\107" +
"\221\011\052\174\255\306\353\215\344\310\254\233\266\202\052\150" +
"\222\313\326\061\311\072\153\034\206\347\355\240\367\251\136\215" +
"\336\312\010\276\077\330\326\060\374\206\317\257\345\375\301\210" +
"\347\217\013\336\037\254\323\035\075\142\357\017\166\032\237\117" +
"\177\177\060\342\005\335\275\136\150\137\335\261\173\177\260\356" +
"\332\156\356\057\042\366\020\057\046\136\102\274\224\070\171\373" +
"\271\330\126\072\207\377\156\145\213\266\064\156\266\022\335\312" +
"\313\306\205\133\241\127\366\353\010\333\252\276\107\221\051\053" +
"\174\255\306\353\215\344\310\054\265\225\037\042\160\232\326\243" +
"\310\004\025\276\126\343\365\106\162\144\226\332\312\217\020\070" +
"\115\353\121\144\202\012\137\253\361\172\043\071\062\113\155\345" +
"\103\010\234\246\365\050\062\101\205\257\325\170\275\221\034\231" +
"\245\266\362\055\004\116\323\172\024\231\240\302\327\152\274\336" +
"\110\216\314\122\133\171\053\002\247\151\075\212\114\120\341\153" +
"\065\136\157\044\107\146\251\255\174\020\201\323\264\036\105\046" +
"\250\360\265\032\257\067\222\043\263\324\126\076\205\300\151\132" +
"\217\042\023\124\370\132\215\327\033\311\221\131\152\053\357\101" +
"\340\064\255\107\221\011\052\174\255\306\353\215\344\310\054\265" +
"\225\357\043\160\232\326\243\310\004\025\276\126\343\365\106\162" +
"\144\226\332\312\327\020\070\115\353\121\144\202\012\137\253\361" +
"\172\043\071\062\113\155\345\055\010\234\246\365\050\062\101\205" +
"\257\325\170\275\221\034\231\245\266\362\006\004\116\323\172\024" +
"\231\240\302\327\152\274\336\110\216\314\122\133\071\200\300\151" +
"\132\217\042\023\124\370\132\215\327\033\311\221\131\152\053\157" +
"\102\340\064\255\107\221\011\052\174\255\306\353\215\344\310\054" +
"\265\225\317\040\160\232\326\243\310\004\025\276\126\343\365\106" +
"\162\144\226\332\312\127\020\070\115\353\121\144\202\012\137\253" +
"\361\172\043\071\062\113\155\345\157\343\055\340\177\220\352\164" +
"\350\367\042\137\316\347\327\375\275\310\127\330\127\167\004\276" +
"\027\151\316\317\176\057\362\057\143\361\126\250\277\162\374\037" +
"\370\177\275\350\361\253\270\357\065\257\117\333\012\361\152\176" +
"\274\233\170\315\270\340\377\365\332\356\152\053\137\106\330\126" +
"\365\075\212\114\122\341\153\065\136\157\044\107\146\251\255\134" +
"\215\320\253\373\153\250\036\321\314\173\156\025\077\173\336\316" +
"\037\005\170\201\103\245\266\362\121\204\136\335\137\103\365\366" +
"\076\213\236\133\164\155\112\067\345\205\172\107\163\144\226\332" +
"\312\227\020\070\115\353\121\144\202\012\137\253\361\172\043\071" +
"\062\113\155\345\253\010\234\246\365\050\062\101\205\257\325\170" +
"\275\221\034\231\165\323\126\366\255\343\366\233\012\125\350\347" +
"\214\057\131\337\074\353\255\161\030\136\273\323\063\040\345\337" +
"\312\070\014\257\133\337\074\233\332\056\377\126\166\252\306\141" +
"\330\327\075\076\175\307\006\131\122\343\060\234\101\354\047\316" +
"\214\336\103\157\145\153\030\176\267\014\071\207\236\237\272\217" +
"\347\036\275\257\327\333\236\217\334\143\131\216\314\122\237\203" +
"\235\215\300\151\132\217\042\023\124\370\132\215\327\033\311\221" +
"\131\063\037\053\277\135\206\234\103\317\117\335\307\163\217\336" +
"\327\353\155\317\107\356\261\054\107\146\251\217\225\327\043\160" +
"\232\326\243\310\004\025\276\126\343\365\106\162\144\126\354\137" +
"\373\161\030\316\132\307\064\233\272\261\146\376\006\373\303\062" +
"\344\034\172\176\352\076\236\173\364\276\136\157\173\076\162\217" +
"\145\071\062\053\374\173\221\007\371\274\374\006\336\301\161\363" +
"\033\170\241\337\300\343\307\147\163\237\363\173\221\321\032\327" +
"\364\367\353\377\153\251\177\355\257\105\350\325\375\065\124\217" +
"\150\346\075\267\212\237\075\157\347\217\002\274\300\241\122\133" +
"\371\030\102\257\356\257\241\172\173\237\105\317\055\272\066\245" +
"\233\362\102\275\243\071\062\153\347\276\342\062\016\303\071\325" +
"\236\267\224\122\037\053\037\107\350\325\375\065\124\217\150\346" +
"\075\267\212\237\075\157\347\217\002\274\300\241\122\133\071\023" +
"\241\127\367\327\120\075\242\231\367\334\052\176\366\274\235\077" +
"\012\360\002\207\112\155\345\257\343\346\347\301\156\176\077\017" +
"\166\026\102\177\227\376\032\252\107\064\363\236\133\305\317\236" +
"\267\363\107\031\326\124\233\367\074\052\360\076\327\253\121\037" +
"\053\027\043\154\253\372\036\105\046\250\360\265\032\257\067\222" +
"\043\263\146\276\016\166\356\062\172\165\177\015\321\316\273\317" +
"\242\347\026\135\363\172\315\363\231\362\216\346\310\254\231\255" +
"\134\264\014\071\207\236\237\272\117\344\036\031\232\250\367\124" +
"\216\314\312\171\177\060\176\134\366\071\030\365\363\106\360\163" +
"\260\316\147\335\237\203\235\157\137\335\161\345\317\301\266\377" +
"\264\065\014\277\137\206\234\103\317\117\335\307\163\017\232\170" +
"\237\350\350\361\351\136\257\125\274\221\034\335\053\173\006\367" +
"\013\354\163\150\315\154\345\374\145\310\071\364\374\324\175\042" +
"\367\310\320\104\275\247\162\144\226\372\034\354\317\010\234\246" +
"\365\050\062\101\205\257\325\170\275\221\034\231\065\363\261\362" +
"\307\145\310\071\364\374\324\175\074\367\350\175\275\336\366\174" +
"\344\036\313\162\144\226\372\130\371\023\002\247\151\075\212\114" +
"\120\341\153\065\136\157\044\107\146\251\255\234\203\320\253\373" +
"\153\250\036\321\314\173\156\025\077\173\336\316\037\005\170\201" +
"\103\025\372\071\343\013\327\065\315\246\156\254\231\177\127\056" +
"\134\206\234\103\317\117\335\047\162\217\014\115\324\173\052\107" +
"\146\315\154\345\274\145\364\352\376\032\242\235\167\237\105\317" +
"\055\272\346\365\232\347\063\345\035\315\221\131\063\133\271\140" +
"\031\162\016\075\077\165\237\310\075\062\064\121\357\251\034\231" +
"\265\371\112\176\201\267\373\153\145\241\255\134\352\165\331\224" +
"\257\376\003\160\315\074\113\063\260\000\000"
});

public static final byte[] shiftableSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\103\176\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\000\322\250\240\242\270\220\241\216\201\251\024\104" +
"\262\202\024\074\257\377\366\375\374\377\344\377\020\151\371\377" +
"\130\024\374\372\177\376\377\153\250\002\373\121\005\243\012\106" +
"\025\214\052\040\105\301\047\244\022\146\320\072\162\170\050\370" +
"\366\375\075\105\205\071\103\003\110\202\205\001\006\260\305\046" +
"\320\204\354\177\370\242\033\250\340\061\076\005\203\041\240\106" +
"\025\014\041\005\004\013\020\112\223\075\021\046\320\274\020\203" +
"\110\060\060\102\151\314\254\007\121\201\047\157\062\020\126\300" +
"\311\200\014\160\144\336\327\024\145\136\112\115\200\110\160\120" +
"\344\110\002\012\040\022\015\370\254\240\064\075\200\204\005\360" +
"\007\065\101\053\050\117\223\004\114\040\242\264\047\024\335\366" +
"\277\100\156\300\027\027\277\011\104\026\345\046\020\126\100\363" +
"\104\113\070\250\031\010\006\065\315\003\212\140\164\143\070\022" +
"\000\230\373\264\255\257\016\000\000"
});

public static final byte[] layoutSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\103\176\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\000\322\250\240\242\270\220\241\216\201\251\024\104" +
"\262\216\052\030\125\060\252\140\124\301\250\202\121\005\243\012" +
"\106\025\214\052\030\125\060\252\140\124\301\250\202\121\005\020" +
"\005\000\236\356\342\371\257\016\000\000"
});

public static final byte[] prefixSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\103\176\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\303\314\000\322\250\240\242\270\220\241\216\201\251\024\104" +
"\262\216\052\030\125\060\252\140\124\301\250\202\121\005\243\012" +
"\106\025\214\052\030\125\060\252\140\124\301\250\202\121\005\020" +
"\005\000\236\356\342\371\257\016\000\000"
});

public static final byte[] prefixMapsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\314\241\016\001\001" +
"\034\300\341\377\156\074\001\345\336\100\272\240\010\232\211\246" +
"\210\227\056\010\147\314\261\073\223\074\202\047\121\074\211\115" +
"\226\065\236\201\242\211\066\345\373\345\337\276\323\043\332\315" +
"\066\272\171\076\131\024\273\042\153\352\162\231\215\312\172\066" +
"\257\207\351\365\171\036\334\157\375\044\142\137\105\304\372\075" +
"\166\276\174\253\264\327\272\034\307\325\347\233\126\077\255\331" +
"\304\041\022\056\227\313\345\162\271\134\056\227\313\345\162\271" +
"\134\056\227\313\345\162\271\134\056\227\313\345\162\271\134\056" +
"\227\313\345\376\333\175\001\174\204\020\256\163\046\000\000"
});

public static final byte[] terminalUsesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\374\030\106\001\131\000\000\020\034\001\300" +
"\123\001\000\000"
});

public static final byte[] shiftableUnionHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\270" +
"\210\101\040\053\261\054\121\257\264\044\063\107\317\051\263\044" +
"\070\265\044\357\157\107\235\245\311\152\105\146\006\306\150\006" +
"\226\244\314\222\342\022\006\246\150\257\212\202\322\042\060\255" +
"\300\262\125\150\143\351\144\046\006\206\212\002\006\006\006\246" +
"\347\377\101\340\375\177\006\060\260\377\137\001\000\237\211\100" +
"\016\131\000\000\000"
});

public static final byte[] acceptSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\335\131\075\113\303\120" +
"\024\275\357\231\102\306\240\223\333\163\163\162\322\101\134\212" +
"\070\211\233\143\247\012\016\025\225\332\246\322\251\074\305\241" +
"\223\010\376\001\167\135\034\334\375\007\376\022\177\202\140\223" +
"\052\110\172\357\113\116\362\142\113\337\320\122\116\356\327\171" +
"\347\335\233\244\057\237\324\030\364\150\255\165\164\326\276\156" +
"\157\015\342\316\371\326\176\047\076\076\215\367\056\326\067\203" +
"\217\373\203\256\046\032\166\211\324\116\277\107\121\366\252\313" +
"\257\361\150\167\373\171\143\205\124\213\202\223\116\334\217\111" +
"\267\016\207\335\211\323\344\333\004\157\253\257\203\307\037\037" +
"\223\317\376\025\215\110\017\222\317\306\344\267\042\113\004\003" +
"\226\007\336\303\060\362\343\112\223\140\321\164\247\153\221\164" +
"\323\025\360\256\102\254\162\001\320\144\033\144\350\167\031\017" +
"\056\247\205\132\066\026\375\135\012\164\051\000\115\207\105\310" +
"\003\326\223\234\246\026\232\007\014\354\112\011\100\300\132\214" +
"\301\164\263\364\007\150\241\012\250\307\070\310\114\122\231\005" +
"\002\311\302\226\335\227\074\006\370\172\222\145\012\153\172\002" +
"\330\042\261\044\227\122\372\134\227\252\267\065\326\033\024\253" +
"\064\227\074\037\301\043\234\043\002\070\022\151\016\140\013\014" +
"\250\251\245\173\004\274\061\340\030\347\306\045\006\266\365\051" +
"\070\253\144\331\074\355\346\135\240\353\146\073\235\037\360\171" +
"\011\353\314\112\071\202\073\331\366\324\351\344\030\121\204\007" +
"\347\167\120\024\271\064\107\014\033\243\374\034\101\346\042\345" +
"\305\240\202\235\257\170\217\367\220\067\076\130\212\336\032\124" +
"\233\070\002\131\271\064\057\060\233\026\252\124\321\223\354\312" +
"\123\367\317\354\130\224\167\001\377\050\200\305\134\324\071\270" +
"\250\200\060\172\110\002\134\312\234\307\035\361\377\160\124\137" +
"\141\265\372\126\160\245\322\160\145\317\001\336\055\221\212\251" +
"\322\074\133\206\174\347\056\374\145\001\222\325\054\073\266\204" +
"\227\173\125\267\266\232\134\340\127\244\225\233\363\203\117\215" +
"\022\167\067\120\332\225\021\006\230\360\140\215\002\145\134\341" +
"\165\260\035\266\330\216\341\301\274\001\067\076\125\341\332\143" +
"\340\374\026\075\330\374\133\141\326\262\122\013\020\117\016\111" +
"\347\130\000\230\053\043\237\374\047\054\057\232\300\054\245\377" +
"\036\314\002\167\036\053\327\165\213\070\253\074\106\314\216\235" +
"\114\365\200\044\321\364\125\117\150\345\254\264\077\316\242\112" +
"\144\142\357\301\322\274\001\213\133\120\147\331\354\240\215\243" +
"\124\355\240\105\210\131\114\205\066\374\006\321\123\334\042\065" +
"\036\000\000"
});

public static final byte[] rejectSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\243\151\161\021\203\000\272\252\274\277\035" +
"\165\226\046\253\025\231\031\030\243\031\130\222\062\113\212\113" +
"\030\230\242\275\052\012\200\206\202\150\005\226\255\102\033\113" +
"\047\103\315\000\222\305\205\014\165\014\114\245\040\222\165\124" +
"\140\124\140\124\140\124\200\002\001\106\040\156\030\104\356\031" +
"\025\030\124\021\066\232\074\106\005\106\274\000\065\063\001\116" +
"\263\106\163\332\250\300\020\110\277\243\002\043\110\200\370\204" +
"\063\232\304\106\203\141\360\006\045\005\146\015\307\030\035\044" +
"\135\210\101\022\053\264\050\344\150\341\065\272\004\327\250\112" +
"\220\004\000\071\261\172\012\325\025\000\000"
});

public static final byte[] possibleSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\127\273\156\023\101" +
"\024\275\063\214\321\226\043\207\302\120\055\035\125\224\002\012" +
"\104\301\012\121\041\212\110\224\156\010\022\205\043\202\114\274" +
"\106\251\242\301\102\042\025\132\211\037\110\017\015\005\075\177" +
"\300\227\360\011\020\146\275\316\103\353\173\146\074\273\143\034" +
"\202\247\260\145\235\373\230\373\230\173\256\277\374\244\316\170" +
"\237\066\372\117\167\167\336\356\154\216\363\301\253\315\107\203" +
"\374\331\313\374\301\336\315\073\352\307\307\307\103\111\164\060" +
"\044\022\367\106\373\244\353\122\257\177\035\035\336\277\373\371" +
"\366\065\022\175\122\057\006\371\050\047\331\177\162\060\264\106" +
"\313\357\124\175\353\176\035\177\232\331\260\237\243\067\164\110" +
"\162\134\176\166\354\157\171\122\235\337\064\075\017\117\346\004" +
"\310\020\151\072\073\151\135\100\130\001\243\031\323\202\276\047" +
"\011\007\370\115\366\220\111\033\007\261\100\146\000\120\371\062" +
"\014\160\214\256\075\365\241\130\123\252\007\174\114\266\101\240" +
"\346\074\320\036\047\320\241\255\363\104\060\246\077\240\133\232" +
"\056\002\110\151\343\317\272\340\064\013\053\220\055\134\310\122" +
"\343\271\043\353\011\013\210\202\110\001\015\157\016\351\026\322" +
"\234\360\001\247\147\232\006\231\316\052\374\072\023\170\151\101" +
"\160\361\331\030\156\050\016\070\302\175\130\372\352\371\312\302" +
"\146\306\350\144\173\336\244\230\152\012\316\127\306\367\106\352" +
"\174\045\222\001\024\322\060\123\200\353\130\173\335\215\014\371" +
"\320\336\306\144\343\051\005\044\073\172\330\136\267\300\061\354" +
"\046\347\350\211\041\100\313\166\341\030\271\260\300\344\234\217" +
"\000\240\253\233\102\215\122\230\300\334\302\024\102\000\122\114" +
"\331\267\000\230\300\361\261\054\212\171\217\156\071\101\300\073" +
"\030\227\106\200\014\317\104\341\032\244\141\054\225\272\172\237" +
"\035\271\242\025\031\261\134\163\261\041\041\031\315\216\134\074" +
"\360\372\245\174\002\374\146\103\232\245\263\042\220\316\146\154" +
"\005\065\064\002\062\304\157\254\051\300\157\125\222\171\122\162" +
"\260\025\014\060\013\240\061\017\000\052\012\233\037\361\132\212" +
"\171\355\302\341\007\242\133\200\142\130\150\071\326\333\013\230" +
"\177\200\173\134\273\156\204\032\264\267\260\256\342\002\125\154" +
"\260\050\374\365\175\140\176\334\326\004\270\277\150\127\143\037" +
"\250\323\242\366\011\044\076\001\171\071\050\231\202\271\032\002" +
"\072\130\303\263\017\314\045\071\346\076\320\200\366\251\301\076" +
"\020\012\104\365\021\161\031\141\337\321\002\004\341\025\060\255" +
"\055\320\045\140\220\165\036\074\054\026\112\075\053\047\216\377" +
"\213\037\212\012\142\207\072\017\270\064\002\371\041\042\161\254" +
"\311\151\105\034\144\272\060\127\131\050\243\264\032\122\353\031" +
"\164\172\226\060\203\274\243\246\341\300\010\327\210\065\142\040" +
"\320\360\225\007\076\346\300\147\263\332\336\216\326\302\361\073" +
"\065\244\021\033\124\166\225\005\154\120\216\145\145\335\335\374" +
"\201\014\020\032\227\367\372\236\247\311\136\302\221\103\144\012" +
"\152\314\200\077\334\131\037\100\205\041\000\000"
});

public static final byte[] cMapHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\321\071\112\204\101" +
"\020\006\320\337\321\161\337\367\175\167\334\367\135\117\140\340" +
"\011\114\074\202\010\212\027\062\063\062\364\110\202\167\360\033" +
"\370\003\043\031\065\223\327\360\150\272\250\252\056\250\227\217" +
"\242\372\160\137\124\156\256\256\337\156\153\217\357\257\317\225" +
"\242\170\272\053\232\212\372\231\157\300\100\064\177\171\117\067" +
"\130\327\150\357\255\150\211\303\062\266\021\213\121\237\160\067" +
"\332\143\065\332\142\057\226\242\047\152\061\026\027\337\070\057" +
"\255\224\275\057\143\056\146\142\274\214\235\376\322\310\017\363" +
"\017\342\070\146\277\374\133\277\327\142\052\066\343\050\172\143" +
"\042\326\143\077\106\243\122\346\157\307\140\164\104\065\272\142" +
"\050\026\242\077\226\243\063\372\142\062\206\143\247\254\155\215" +
"\263\350\216\223\077\354\014\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\200\177\356\023\066\062\220\274\033\000\004\000"
});

public static final byte[] deltaHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\235\007\230\024\065" +
"\030\206\023\340\350\275\017\275\367\336\016\020\261\034\242\250" +
"\210\275\235\005\173\107\101\101\100\051\052\012\130\300\356\331" +
"\005\304\012\052\330\020\260\202\275\167\301\206\210\015\305\202" +
"\005\273\146\327\314\022\147\063\163\231\233\044\223\231\077\373" +
"\074\337\163\231\077\323\336\375\376\314\144\167\047\271\105\233" +
"\120\301\370\161\250\174\161\361\210\106\133\066\214\162\346\316" +
"\036\122\016\241\211\247\041\204\373\223\170\271\342\021\043\227" +
"\217\356\060\141\343\322\205\064\214\206\040\140\257\361\143\321" +
"\124\104\350\163\344\231\162\171\242\012\104\005\104\025\211\052" +
"\021\125\046\252\102\124\225\326\147\124\215\250\072\121\015\242" +
"\232\064\126\213\250\066\263\116\106\165\210\352\022\325\043\252" +
"\317\304\033\020\065\044\152\104\324\230\310\041\152\102\324\224" +
"\250\031\121\163\242\026\104\055\211\132\021\265\246\333\265\041" +
"\152\113\324\216\250\075\215\165\040\352\110\313\235\210\072\023" +
"\165\041\352\112\324\215\306\273\023\365\240\352\231\107\336\213" +
"\250\067\121\037\372\067\243\276\114\271\267\100\134\124\375\042" +
"\156\057\242\376\176\165\171\344\231\367\175\000\121\041\123\036" +
"\110\313\356\362\000\117\234\215\171\227\203\352\006\061\373\145" +
"\367\317\053\073\234\375\170\143\101\333\347\255\237\107\076\230" +
"\243\260\361\104\050\217\174\033\252\314\362\266\264\074\224\211" +
"\263\362\213\213\152\273\210\333\213\150\173\277\072\355\331\276" +
"\003\055\357\210\114\313\366\201\124\105\114\131\225\206\151\070" +
"\206\257\270\236\073\214\347\216\307\163\107\040\056\052\236\347" +
"\332\144\311\043\265\363\235\220\170\073\067\367\256\146\333\271" +
"\354\154\037\316\211\305\221\355\303\335\163\121\172\127\333\231" +
"\123\267\013\062\065\333\145\222\313\156\347\043\224\223\107\311" +
"\366\135\175\326\345\051\216\154\317\251\124\317\167\023\360\174" +
"\167\115\236\217\124\356\171\120\266\357\301\041\367\322\215\122" +
"\104\256\276\235\207\311\366\075\175\352\222\231\355\141\310\243" +
"\034\335\074\162\066\333\367\102\346\134\333\367\106\072\262\175" +
"\037\220\236\073\110\177\266\357\013\226\034\236\347\373\321\362" +
"\376\340\310\315\362\074\154\277\375\000\244\347\332\156\126\117" +
"\106\205\347\007\306\346\271\116\362\203\164\120\012\223\263\331" +
"\176\060\322\333\223\051\366\034\213\255\073\004\361\263\375\120" +
"\237\155\222\227\355\361\171\156\032\371\141\104\207\203\044\327" +
"\347\171\330\273\232\273\074\232\263\216\275\253\045\317\363\043" +
"\220\071\237\122\217\344\170\176\124\342\075\077\332\070\317\303" +
"\264\163\077\317\217\221\344\271\155\347\352\074\267\344\260\310" +
"\217\145\310\217\003\105\236\211\206\275\302\035\217\202\257\160" +
"\262\356\152\047\040\035\127\270\023\005\274\215\352\371\111\306" +
"\171\356\040\210\355\074\315\344\047\113\045\077\105\360\250\143" +
"\014\040\167\317\143\214\121\236\237\252\373\135\060\206\134\273" +
"\354\123\336\071\362\314\370\206\261\036\215\343\304\202\342\046" +
"\351\164\277\272\074\362\063\250\227\343\351\137\225\232\240\341" +
"\030\147\372\325\371\146\373\104\004\055\333\047\121\025\062\345" +
"\311\114\171\222\100\234\325\131\001\165\147\013\154\257\114\211" +
"\172\036\116\355\067\023\160\107\163\270\117\304\302\175\342\167" +
"\012\123\206\321\223\201\233\355\160\307\056\115\145\064\215\376" +
"\235\356\211\117\055\045\156\222\316\361\253\263\237\130\162\344" +
"\103\251\316\045\072\217\131\126\241\031\212\367\237\321\371\176" +
"\165\276\355\374\002\004\255\235\317\244\052\144\312\263\230\362" +
"\114\201\070\253\331\001\165\027\012\154\257\114\266\367\372\077" +
"\362\213\100\222\203\171\371\366\333\057\106\212\372\313\214\314" +
"\353\267\253\156\347\227\040\023\263\335\135\123\166\117\146\016" +
"\047\306\043\127\255\071\356\271\030\175\155\237\253\335\163\225" +
"\344\227\206\040\327\237\355\161\172\176\131\110\362\313\245\222" +
"\073\250\354\355\074\075\243\063\343\154\347\121\263\375\212\320" +
"\236\233\102\176\145\104\362\144\265\163\366\154\257\112\014\371" +
"\325\222\311\243\146\273\032\362\153\064\170\256\237\334\101\362" +
"\373\160\045\234\030\217\134\265\112\334\163\061\256\235\137\033" +
"\253\347\246\134\341\104\310\257\223\106\016\367\227\006\125\355" +
"\234\247\070\332\171\116\226\334\222\147\243\145\275\302\135\317" +
"\054\337\200\324\136\341\156\344\304\202\266\217\357\176\236\014" +
"\317\341\222\047\351\176\256\267\047\163\023\130\362\070\075\277" +
"\071\166\362\133\142\042\127\347\371\074\252\102\246\074\237\051" +
"\317\023\210\213\152\120\304\355\043\051\217\174\001\325\255\114" +
"\171\041\123\136\040\020\027\325\155\041\326\275\075\342\261\362" +
"\044\265\235\337\301\131\047\071\331\056\343\012\167\047\070\362" +
"\273\300\173\256\212\374\156\345\344\016\262\275\127\371\236\057" +
"\212\340\271\332\154\137\114\125\310\224\357\141\312\213\005\342" +
"\242\272\067\342\366\042\272\317\257\056\217\174\011\125\041\123" +
"\136\312\224\227\010\304\105\165\177\304\355\105\364\200\137\235" +
"\366\154\177\020\231\232\355\252\311\303\134\333\037\322\112\156" +
"\277\165\176\230\051\303\271\253\145\004\363\176\256\202\174\231" +
"\041\344\313\334\163\261\343\317\155\266\003\276\266\303\035\205" +
"\375\010\045\112\213\226\373\325\345\221\257\240\032\314\224\127" +
"\062\345\025\002\361\104\310\216\113\265\331\236\233\155\341\121" +
"\004\155\266\205\311\124\105\114\071\212\036\013\250\173\134\322" +
"\061\312\044\173\127\263\363\311\330\131\125\244\173\376\004\047" +
"\026\227\347\331\163\261\363\311\330\117\054\200\347\034\200\353" +
"\371\164\106\117\172\226\223\250\247\374\352\362\310\127\121\015" +
"\146\312\253\231\362\052\201\170\042\144\075\267\237\122\163\163" +
"\157\074\215\240\315\055\062\213\252\210\051\107\321\063\001\165" +
"\317\112\072\106\231\144\357\347\166\336\050\073\207\220\164\317" +
"\237\343\304\342\362\074\173\056\166\366\044\043\237\231\170\236" +
"\123\247\356\231\011\060\057\243\075\327\377\204\220\212\137\221" +
"\323\071\237\314\013\251\361\074\054\371\213\140\311\323\323\316" +
"\055\271\037\371\113\140\311\343\362\374\145\260\344\354\176\136" +
"\111\065\371\253\001\344\352\075\177\315\120\317\325\220\073\110" +
"\176\037\256\204\023\343\221\253\126\011\212\163\076\231\327\215" +
"\365\274\254\344\157\010\222\233\233\355\054\371\233\012\074\117" +
"\006\071\317\363\267\300\222\277\015\226\334\233\355\357\200\045" +
"\327\351\371\273\106\221\277\227\012\317\341\316\266\060\237\252" +
"\210\051\253\322\060\015\307\360\225\235\147\042\107\276\206\243" +
"\265\041\343\052\364\276\354\175\346\221\057\244\372\200\051\253" +
"\322\207\041\326\375\110\366\361\175\263\375\143\246\014\043\333" +
"\327\121\175\302\224\327\063\345\165\002\161\121\175\052\270\336" +
"\206\210\307\341\112\312\375\374\063\224\206\373\271\050\371\347" +
"\310\366\333\021\372\002\054\271\273\374\045\130\162\331\236\177" +
"\245\225\174\043\135\003\336\130\344\257\021\324\247\077\135\317" +
"\341\375\357\314\157\120\272\346\020\362\125\340\057\015\233\100" +
"\171\356\266\203\114\073\377\026\251\155\153\146\265\163\367\075" +
"\231\002\316\363\045\150\353\214\131\337\241\344\317\230\345\053" +
"\243\173\062\336\076\334\367\110\145\117\306\144\362\037\224\222" +
"\303\035\257\346\276\107\262\173\257\233\071\261\070\256\160\233" +
"\335\163\261\117\374\346\310\127\122\261\345\124\312\316\266\140" +
"\147\125\001\114\256\152\236\211\037\071\261\011\222\217\041\072" +
"\317\104\366\134\354\114\072\166\126\025\070\257\074\362\325\124" +
"\154\071\225\262\043\162\355\234\003\200\311\125\215\105\376\211" +
"\023\233\041\161\377\141\306\042\147\317\305\316\266\140\107\336" +
"\033\375\155\224\337\057\212\077\013\154\037\375\173\270\137\014" +
"\044\117\377\067\220\354\176\266\200\045\167\377\376\012\226\074" +
"\310\363\337\100\221\377\236\112\317\377\010\351\171\262\310\377" +
"\214\350\171\162\311\203\074\377\313\070\362\277\065\221\307\357" +
"\371\077\061\171\036\077\171\310\154\307\010\052\171\030\317\061" +
"\206\112\236\054\317\161\071\250\344\051\361\174\015\107\060\106" +
"\360\254\065\123\270\274\354\175\172\311\161\005\216\012\102\306" +
"\145\253\242\212\375\132\317\275\343\325\160\045\044\171\174\030" +
"\107\146\215\127\133\377\237\160\345\255\145\125\302\125\004\327" +
"\253\252\342\370\366\332\156\107\344\002\366\334\216\310\205\067" +
"\042\327\241\202\067\152\053\023\215\363\363\171\065\317\261\034" +
"\316\261\035\316\376\170\333\044\352\263\032\256\016\225\074\231" +
"\237\317\161\015\150\344\270\146\152\074\167\020\304\153\073\073" +
"\122\117\365\150\271\141\032\216\041\076\122\017\356\050\154\233" +
"\355\360\262\235\365\034\326\270\324\114\264\224\273\032\256\345" +
"\211\203\357\311\310\046\307\265\265\223\273\331\136\132\126\207" +
"\311\166\363\307\050\302\375\117\261\252\236\165\066\377\211\137" +
"\301\166\216\353\040\173\205\063\226\034\327\115\052\071\256\147" +
"\272\347\270\276\231\236\247\047\333\161\003\250\344\361\172\216" +
"\033\102\045\327\351\071\156\004\211\034\067\266\236\103\043\307" +
"\176\357\102\372\311\233\100\045\207\233\355\062\311\161\323\060" +
"\344\331\147\334\114\123\063\025\373\265\117\213\330\047\001\001" +
"\317\222\146\200\277\061\171\156\000\145\114\344\364\251\051\200" +
"\317\275\302\376\135\315\220\236\014\156\316\251\113\124\037\016" +
"\267\050\033\271\355\275\132\362\320\375\366\226\151\046\307\255" +
"\004\174\116\045\171\366\072\322\072\056\162\334\046\136\362\370" +
"\075\307\155\241\222\303\365\334\222\313\042\307\355\300\222\267" +
"\207\112\016\067\333\243\222\343\016\151\044\307\035\115\360\034" +
"\167\062\323\163\270\331\036\222\034\314\313\172\056\245\337\336" +
"\031\052\071\134\317\145\223\343\056\046\222\343\256\326\163\113" +
"\156\311\325\221\343\156\111\043\307\335\255\347\151\043\307\075" +
"\240\222\003\366\274\047\124\162\300\236\367\202\112\016\327\163" +
"\135\344\270\067\124\162\163\075\307\175\240\222\303\365\134\066" +
"\071\356\013\225\334\174\317\161\077\250\344\311\364\374\137\250" +
"\353\005\110\353\055\001\000"
});

public static void initArrays()
throws java.io.IOException,java.lang.ClassNotFoundException
{
    symbolNames = (String[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(symbolNamesHash);
    symbolDisplayNames = (String[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(symbolDisplayNamesHash);
    symbolNumbers = (int[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(symbolNumbersHash);
    productionLHSs = (int[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(productionLHSsHash);
    parseTable = (int[][]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(parseTableHash);
    shiftableSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(shiftableSetsHash);
    layoutSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(layoutSetsHash);
    prefixSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(prefixSetsHash);
    prefixMaps = (java.util.BitSet[][]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(prefixMapsHash);
    terminalUses = (int[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(terminalUsesHash);
    shiftableUnion = (java.util.BitSet) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(shiftableUnionHash);
    acceptSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(acceptSetsHash);
    rejectSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(rejectSetsHash);
    possibleSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(possibleSetsHash);
    cmap = (int[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(cMapHash);
    delta = (int[][]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(deltaHash);
    }
public WyvernLexer() {}

		private static int TERMINAL_COUNT;
		private static int GRAMMAR_SYMBOL_COUNT;
		private static int SYMBOL_COUNT;
		private static int PARSER_STATE_COUNT;
		private static int SCANNER_STATE_COUNT;
		private static int DISAMBIG_GROUP_COUNT;
		
		private static int SCANNER_START_STATENUM;
		private static int PARSER_START_STATENUM;
		private static int EOF_SYMNUM;
		private static int EPS_SYMNUM;
		
		private static String[] symbolNames;
		private static String[] symbolDisplayNames;
		private static int[] symbolNumbers;
		private static int[] productionLHSs;
		
		private static int[][] parseTable;
		private static java.util.BitSet[] shiftableSets;
		private static java.util.BitSet[] layoutSets;
		private static java.util.BitSet[] prefixSets;
		private static java.util.BitSet[][] prefixMaps;
		private static int[] terminalUses;
		
		private static java.util.BitSet[] disambiguationGroups;
		
		private static java.util.BitSet shiftableUnion;
		
		private static java.util.BitSet[] acceptSets,rejectSets,possibleSets;
		
		private static int[][] delta;
		private static int[] cmap;
		
		public int getTERMINAL_COUNT() {
			return TERMINAL_COUNT;
		}
		public int getGRAMMAR_SYMBOL_COUNT() {
			return GRAMMAR_SYMBOL_COUNT;
		}
		public int getSYMBOL_COUNT() {
			return SYMBOL_COUNT;
		}
		public int getPARSER_STATE_COUNT() {
			return PARSER_STATE_COUNT;
		}
		public int getSCANNER_STATE_COUNT() {
			return SCANNER_STATE_COUNT;
		}
		public int getDISAMBIG_GROUP_COUNT() {
			return DISAMBIG_GROUP_COUNT;
		}
		public int getSCANNER_START_STATENUM() {
			return SCANNER_START_STATENUM;
		}
		public int getPARSER_START_STATENUM() {
			return PARSER_START_STATENUM;
		}
		public int getEOF_SYMNUM() {
			return EOF_SYMNUM;
		}
		public int getEPS_SYMNUM() {
			return EPS_SYMNUM;
		}
		public String[] getSymbolNames() {
			return symbolNames;
		}
		public String[] getSymbolDisplayNames() {
			return symbolDisplayNames;
		}
		public int[] getSymbolNumbers() {
			return symbolNumbers;
		}
		public int[] getProductionLHSs() {
			return productionLHSs;
		}
		public int[][] getParseTable() {
			return parseTable;
		}
		public java.util.BitSet[] getShiftableSets() {
			return shiftableSets;
		}
		public java.util.BitSet[] getLayoutSets() {
			return layoutSets;
		}
		public java.util.BitSet[] getPrefixSets() {
			return prefixSets;
		}
		public java.util.BitSet[][] getLayoutMaps() {
			return null;
		}
		public java.util.BitSet[][] getPrefixMaps() {
			return prefixMaps;
		}
		public int[] getTerminalUses() {
			return terminalUses;
		}
		public java.util.BitSet[] getDisambiguationGroups() {
			return disambiguationGroups;
		}
		public java.util.BitSet getShiftableUnion() {
			return shiftableUnion;
		}
		public java.util.BitSet[] getAcceptSets() {
			return acceptSets;
		}
		public java.util.BitSet[] getRejectSets() {
			return rejectSets;
		}
		public java.util.BitSet[] getPossibleSets() {
			return possibleSets;
		}
		public int[][] getDelta() {
			return delta;
		}
		public int[] getCmap() {
			return cmap;
		}	
    public List< Token > parse(java.io.Reader input,String inputName)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
    this.charBuffer = edu.umn.cs.melt.copper.runtime.io.ScannerBuffer.instantiate(input);
    setupEngine();
    startEngine(edu.umn.cs.melt.copper.runtime.io.InputPosition.initialPos(inputName));
    List< Token > parseTree = (List< Token >) runEngine();
    return parseTree;
    }


	/********************** LEXER STATE ************************/
	boolean foundTilde = false;						// is there a tilde ~ in the current line?
	boolean DSLNext = false;						// is the next line a DSL?
	boolean inDSL = false;							// are we in a DSL?
	Stack<String> indents = new Stack<String>();	// the stack of indents
	Token flagTok = null;							// a token that signals whether an indent is for a DSL
	Token lastIndent = null;
    public FileLocation startLocation = null;
    boolean ilineNext = false;                      // is the first iline in a sequence next?
    boolean isEQARROWlast = false;
    Stack<String> lambdas = new Stack<String>();	// the stack of indents for lambdas
    Stack<Stack<String>> metaStack = new Stack<Stack<String>>(); // the stack of indentation stacks, for handling multi-line lambdas correctly
	
	/********************** HELPER FUNCTIONS ************************/

    void adjustEQARROW(List list) {
        if (list.size() > 0) {
            Token t = (Token) list.get(list.size()-1);
            if (t.kind == EQARROW) {
                isEQARROWlast = true;
            } else if (t.kind != WHITESPACE && t.kind != SINGLE_LINE_COMMENT
                        && t.kind != MULTI_LINE_COMMENT) {
                isEQARROWlast = false;
            }
        }
    }
    
	/** equivalent (except for "if") to DSLNext */
	boolean isDSLNext() {
	    if (flagTok == null)
	    	return true;
		switch (flagTok.kind) {
		  case TILDE:
		  		return true;
		  case TYPE:
		  case DATATYPE:
		  case DEF:
		  case NEW:
		  case MATCH:
		  case EQARROW:
		  		return false;
		  default:
		  		throw new RuntimeException("broke invariant!");
		}
	}

	/** Wraps the lexeme s in a Token, setting the begin line/column and kind appropriately
	 *  The current lexical location is used.
	 */
	Token token(int kind, String s) {
		// Copper starts counting columns at 0, but we want to follow convention and count columns starting at 1
		int startLine = startLocation==null?1:startLocation.getLine();
        int startChar = startLocation==null?0:startLocation.getCharacter();
        return LexerUtils.makeToken(kind, s, virtualLocation.getLine()+startLine-1, virtualLocation.getColumn()+startChar+1);
	}

    /**
     * Find occurrences of escape sequences in the input string and replaces them with the
     * appropriate character.
     */
    String replaceEscapeSequences(String s) {
        StringBuilder new_s = new StringBuilder();

        int i;
        for (i = 0; i < s.length() - 1; ++i) {
            char c = s.charAt(i);

            if (c == '\\') {
                switch (s.charAt(i + 1)) {
                    case '\'':
                        c = '\''; ++i;
                        break;

                    case '\"':
                        c = '\"'; ++i;
                        break;

                    case '\\':
                        c = '\\'; ++i;
                        break;

                    case 'b':
                        c = '\b'; ++i;
                        break;

                    case 'f':
                        c = '\f'; ++i;
                        break;

                    case 'n':
                        c = '\n'; ++i;
                        break;

                    case 'r':
                        c = '\r'; ++i;
                        break;

                    case 't':
                        c = '\t'; ++i;
                        break;

                    default:
                        ToolError.reportError(ErrorMessage.ILLEGAL_ESCAPE_SEQUENCE,
                                              new FileLocation(virtualLocation.getFileName(),
                                                               virtualLocation.getLine(),
                                                               virtualLocation.getColumn() + i + 2));
                }
            }

            new_s.append(c);
        }

        if (i < s.length()) {
            char c = s.charAt(i);
            if (c == '\\') {
                ToolError.reportError(ErrorMessage.UNCLOSED_STRING_LITERAL,
                                      new FileLocation(virtualLocation.getFileName(),
                                                       virtualLocation.getLine(),
                                                       virtualLocation.getColumn() + 1));
            }

            new_s.append(s.charAt(i));
        }

        return new_s.toString();
    }



    static
    {
        TERMINAL_COUNT = 78;
        GRAMMAR_SYMBOL_COUNT = 99;
        SYMBOL_COUNT = 199;
        PARSER_STATE_COUNT = 111;
        SCANNER_STATE_COUNT = 309;
        DISAMBIG_GROUP_COUNT = 4;
        SCANNER_START_STATENUM = 1;
        PARSER_START_STATENUM = 1;
        EOF_SYMNUM = 0;
        EPS_SYMNUM = -1;
        try { initArrays(); }
        catch(java.io.IOException ex) { ex.printStackTrace(); System.exit(1); }
        catch(java.lang.ClassNotFoundException ex) { ex.printStackTrace(); System.exit(1); }
        disambiguationGroups = new java.util.BitSet[4];
        disambiguationGroups[0] = newBitVec(78,29,42);
        disambiguationGroups[1] = newBitVec(78,35,54);
        disambiguationGroups[2] = newBitVec(78,35,43,54);
        disambiguationGroups[3] = newBitVec(78,40,77);
    }

}
