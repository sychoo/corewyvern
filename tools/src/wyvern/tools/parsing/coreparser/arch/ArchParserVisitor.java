/* Generated By:JavaCC: Do not edit this line. ArchParserVisitor.java Version 6.0_1 */
package wyvern.tools.parsing.coreparser.arch;

public interface ArchParserVisitor
{
  public Object visit(SimpleNode node, Object data);
  public Object visit(ASTArchDesc node, Object data);
  public Object visit(ASTComponentTypeDecl node, Object data);
  public Object visit(ASTConnectorTypeDecl node, Object data);
  public Object visit(ASTArchitectureTypeDecl node, Object data);
  public Object visit(ASTComponentDecl node, Object data);
  public Object visit(ASTConnectorDecl node, Object data);
  public Object visit(ASTAttachmentDecl node, Object data);
  public Object visit(ASTBindingDecl node, Object data);
  public Object visit(ASTEntryPointDecl node, Object data);
  public Object visit(ASTPortDecl node, Object data);
  public Object visit(ASTValDecl node, Object data);
}
/* JavaCC - OriginalChecksum=48eeb83c85bd9cd756dac35da8f44dbd (do not edit this line) */