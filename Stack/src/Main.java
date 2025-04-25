import java.util.Stack;

class TextEditor {
    private String currentText;
    private Stack<String> undoStack;
    private Stack<String> redoStack;

    public TextEditor() {
        currentText = "";
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void addText(String text) {
        undoStack.push(currentText);
        currentText += text;
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText);
            currentText = undoStack.pop();
        } else {
            System.out.println("Tidak ada text yang bisa diundo");
        }
}

public void redo() {
    if (!redoStack.isEmpty()) {
        undoStack.push(currentText);
        currentText = redoStack.pop();
    } else {
        System.out.println("Tidak ada text yang bisa diredo");
    }
}


public void showText(String label) {
    System.out.println(label + ": " + currentText);
 }
}

public class Main {
    public static void main(String[] args) throws Exception {
        TextEditor editor = new TextEditor();

        editor.addText("Selamat");
        editor.addText(" datang");
        editor.showText("Teks saat ini");

        editor.undo();
        editor.showText("Undo");

        editor.redo();
        editor.showText("Redo");
    }
}
