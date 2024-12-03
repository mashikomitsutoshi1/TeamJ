package tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action {

    // 抽象メソッド。リクエストとレスポンスを受け取り。各アクションで実行される処理を定義
    public abstract void execute(
            HttpServletRequest req, HttpServletResponse res
    ) throws Exception;

}
