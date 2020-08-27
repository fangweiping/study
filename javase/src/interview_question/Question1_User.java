package interview_question;


import java.util.HashMap;

import static java.util.Objects.hash;

public class Question1_User {

    public Question1_User(long uuid, String email, String tel, String qq) {
        this.uuid = uuid;
        this.email = email;
        this.tel = tel;
        this.qq = qq;
    }

    public Question1_User() {
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }


    long uuid;
    String email;
    String tel;
    String qq;

    //private static HashMap<Object, Integer> hashCodeMap = new HashMap<>();
    //
    //@Override
    //public int hashCode() {
    //    if (hashCodeMap.containsKey(this.email)) {
    //
    //        return hashCodeMap.get(this.email);
    //
    //    } else if (hashCodeMap.containsKey(this.tel)) {
    //
    //        return hashCodeMap.get(this.tel);
    //
    //    } else if (hashCodeMap.containsKey(this.qq)) {
    //
    //        hashCodeMap.put(this.qq, hash(this.qq));
    //        return hashCodeMap.get(this.qq);
    //
    //    } else {
    //        hashCodeMap.put(this.email, hash(this.email));
    //        hashCodeMap.put(this.tel, hash(this.tel));
    //        hashCodeMap.put(this.qq, hash(this.qq));
    //        return hash(this.email);
    //    }
    //}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question1_User that = (Question1_User) o;
        if (email != null ? !email.equals(that.email) : that.email != null) return true;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return true;
        return qq != null ? qq.equals(that.qq) : that.qq == null;
    }


    @Override
    public String toString() {
        return "Question1_User{" +
                "uuid=" + uuid +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", qq='" + qq + '\'' +
                '}';
    }
}
