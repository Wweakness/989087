import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpSever {
    private static final int PORT=80;  //端口号

    //使用处理器核数作为运行的线程数
    private static final int COUNT=Runtime.getRuntime().availableProcessors();
    private static final ExecutorService EXE= Executors.newFixedThreadPool(COUNT);  //线程池

    public static void main(String[] args) {
        try {
            ServerSocket sever=new ServerSocket(PORT);
            while(true){
                Socket socket= sever.accept();//客户端连接
                EXE.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream is=socket.getInputStream();
                            InputStreamReader isr=new InputStreamReader(is);
                            BufferedReader br=new BufferedReader(isr);
                            String line;
                            //如果此处用while循环，则一直读取客户端发来的输入数据，陷入死循环，其他的线程就进不来，此处会阻塞
                           // while((line=br.readLine())!=null){}
                            String requestLine=br.readLine();  //读取请求行
                            String requestHeader;
                            while((requestHeader=br.readLine()).length()!=0){

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
