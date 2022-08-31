package com.patrickhogg.flashquiz.objects;

import java.util.Objects;

/**
 * @author Patrick Hogg
 */
public class Card {
    private String questionText;
    private String questionAnswer;

    public Card(String questionText, String questionAnswer) {
        this.questionText = questionText;
        this.questionAnswer = questionAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Card card)) {
            return false;
        }
        return Objects.equals(questionText, card.questionText)
               && Objects.equals(questionAnswer, card.questionAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionText, questionAnswer);
    }

    @Override
    public String toString() {
        return "Card{" + "questionText='" + questionText + '\''
               + ", questionAnswer='" + questionAnswer + '\'' + '}';
    }
}
