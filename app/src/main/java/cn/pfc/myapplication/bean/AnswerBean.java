package cn.pfc.myapplication.bean;

import java.util.List;

import cn.pfc.myapplication.util.StringUtil;

public class AnswerBean {

    String questio;
    List<String> option;
    String answer;

    public String getQuestio() {
        return questio;
    }

    public void setQuestio(String questio) {
        this.questio = questio;
    }

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
