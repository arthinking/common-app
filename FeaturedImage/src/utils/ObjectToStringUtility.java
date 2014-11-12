package utils;

import com.example.bean.AccountBean;
import com.example.bean.AtUserBean;
import com.example.bean.CommentBean;
import com.example.bean.CommentListBean;
import com.example.bean.DMBean;
import com.example.bean.DMListBean;
import com.example.bean.DMUserBean;
import com.example.bean.DMUserListBean;
import com.example.bean.EmotionBean;
import com.example.bean.FavBean;
import com.example.bean.FavListBean;
import com.example.bean.GeoBean;
import com.example.bean.GroupBean;
import com.example.bean.GroupListBean;
import com.example.bean.MessageBean;
import com.example.bean.MessageListBean;
import com.example.bean.MessageReCmtCountBean;
import com.example.bean.NearbyStatusListBean;
import com.example.bean.RepostListBean;
import com.example.bean.SearchStatusListBean;
import com.example.bean.SearchUserBean;
import com.example.bean.ShareListBean;
import com.example.bean.TagBean;
import com.example.bean.TopicResultListBean;
import com.example.bean.UnreadBean;
import com.example.bean.UserBean;
import com.example.bean.UserListBean;

/**
 * User: qii
 * Date: 13-3-29
 */
public class ObjectToStringUtility {

    public static String toString(AccountBean account) {
        return account.getUsernick();
    }

    public static String toString(AtUserBean user) {
        return String.format("nickname=%s,remark=%s", user.getNickname(), user.getRemark());
    }

    public static String toString(CommentBean comment) {
        UserBean userBean = comment.getUser();
        String username = (userBean != null ? userBean.getScreen_name() : "user is null");
        return String.format("%s @%s:%s", TimeUtility.getListTime(comment.getMills()), username, comment.getText());
    }

    public static String toString(CommentListBean commentList) {
        StringBuilder builder = new StringBuilder();
        for (CommentBean comment : commentList.getItemList()) {
            builder.append(comment.toString());
        }
        return builder.toString();
    }

    public static String toString(DMListBean listBean) {
        StringBuilder builder = new StringBuilder();
        for (DMBean data : listBean.getItemList()) {
            builder.append(data.toString());
        }
        return builder.toString();
    }

    public static String toString(MessageBean msg) {
        UserBean userBean = msg.getUser();
        String username = (userBean != null ? userBean.getScreen_name() : "user is null");
        return String.format("%s @%s:%s", TimeUtility.getListTime(msg.getMills()), username, msg.getText());
    }

    public static String toString(DMBean dm) {
        UserBean userBean = dm.getUser();
        String username = (userBean != null ? userBean.getScreen_name() : "user is null");
        return String.format("%s @%s:%s", TimeUtility.getListTime(dm.getMills()), username, dm.getText());
    }

    public static String toString(DMUserBean dm) {
        UserBean userBean = dm.getUser();
        String username = (userBean != null ? userBean.getScreen_name() : "user is null");
        return String.format("%s @%s:%s", TimeUtility.getListTime(dm.getMills()), username, dm.getText());
    }

    public static String toString(DMUserListBean listBean) {
        StringBuilder builder = new StringBuilder();
        for (DMUserBean data : listBean.getItemList()) {
            builder.append(data.toString());
        }
        return builder.toString();
    }

    public static String toString(EmotionBean bean) {
        return bean.getPhrase();
    }

    public static String toString(FavBean bean) {
        return toString(bean.getStatus());
    }

    public static String toString(FavListBean listBean) {
        StringBuilder builder = new StringBuilder();
        for (FavBean data : listBean.getFavorites()) {
            builder.append(data.toString());
        }
        return builder.toString();
    }

    public static String toString(GeoBean bean) {
        double[] c = bean.getCoordinates();
        return "type=" + bean.getType() + "coordinates=" + "[" + c[0] + "," + c[1] + "]";
    }

    public static String toString(GroupBean bean) {
        return "group id=" + bean.getIdstr() + "," + "name=" + bean.getName();
    }

    public static String toString(GroupListBean listBean) {
        StringBuilder builder = new StringBuilder();
        for (GroupBean data : listBean.getLists()) {
            builder.append(data.toString());
        }
        return builder.toString();
    }

    public static String toString(MessageListBean listBean) {
        StringBuilder builder = new StringBuilder();
        for (MessageBean data : listBean.getItemList()) {
            builder.append(data.toString());
        }
        return builder.toString();
    }

    public static String toString(MessageReCmtCountBean bean) {
        return "message id=" + bean.getId() + "," + "reposts=" + bean.getReposts()
                + "," + "comments=" + bean.getComments();
    }

    public static String toString(NearbyStatusListBean listBean) {
        StringBuilder builder = new StringBuilder();
        for (MessageBean data : listBean.getItemList()) {
            builder.append(data.toString());
        }
        return builder.toString();
    }

    public static String toString(RepostListBean listBean) {
        StringBuilder builder = new StringBuilder();
        for (MessageBean data : listBean.getItemList()) {
            builder.append(data.toString());
        }
        return builder.toString();
    }

    public static String toString(SearchStatusListBean listBean) {
        StringBuilder builder = new StringBuilder();
        for (MessageBean data : listBean.getItemList()) {
            builder.append(data.toString());
        }
        return builder.toString();
    }

    public static String toString(SearchUserBean bean) {
        return "user id=" + bean.getUid() + "," + "name=" + bean.getScreen_name();
    }

    public static String toString(ShareListBean listBean) {
        StringBuilder builder = new StringBuilder();
        for (MessageBean data : listBean.getItemList()) {
            builder.append(data.toString());
        }
        return builder.toString();
    }

    public static String toString(TagBean bean) {
        return "tag id=" + bean.getId() + "," + "name=" + bean.getName();
    }

    public static String toString(TopicResultListBean listBean) {
        StringBuilder builder = new StringBuilder();
        for (MessageBean data : listBean.getItemList()) {
            builder.append(data.toString());
        }
        return builder.toString();
    }

    public static String toString(UnreadBean bean) {
        return "unread count: mention comments=" + bean.getMention_cmt()
                + "," + "mention weibos=" + bean.getMention_status()
                + "," + "comments" + bean.getCmt();
    }

    public static String toString(UserListBean listBean) {
        StringBuilder builder = new StringBuilder();
        for (UserBean data : listBean.getUsers()) {
            builder.append(data.toString());
        }
        return builder.toString();
    }

    public static String toString(UserBean bean) {
        return "user id=" + bean.getId()
                + "," + "name=" + bean.getScreen_name();
    }
}
