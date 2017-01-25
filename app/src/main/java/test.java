import android.util.Log;

/**
 * Created by lihongxin on 2017/1/24.
 */

public class test {
    String string="F:\\FTP\\FTPServer";
    String delete=string.substring(string.indexOf("\\"));
    test(){
        Log.i("delete",delete);
    }
}
