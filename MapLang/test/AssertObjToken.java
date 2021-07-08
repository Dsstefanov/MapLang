public class AssertObjToken {
    private int lineNumber;
    private int position;
    private String text;
    private Class type;

    public AssertObjToken(int lineNumber, int position, String text, Class type) {
        this.lineNumber = lineNumber;
        this.position = position;
        this.text = text;
        this.type = type;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getPosition() {
        return position;
    }

    public String getText() {
        return text;
    }

    public Class getType() {return type; }
}
