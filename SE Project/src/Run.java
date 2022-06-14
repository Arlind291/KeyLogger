import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class Run {
    private static ManageService service = new ManageService();
    public static void main(String[] args) {

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        GlobalScreen.getInstance().addNativeKeyListener(service.getKeyBoard());
    }
}