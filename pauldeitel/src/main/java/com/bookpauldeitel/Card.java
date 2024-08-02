package com.bookpauldeitel;

public class Card {

    static enum Face {
        Ace, Deuce, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King
    };

    static enum Suit {
        Clubs, Diamonds, Hearts, Spades
    };

    private final Face face;
    private final Suit suit;

    public Card(Face cardFace, Suit cardSuit) {
        this.face = cardFace;
        this.suit = cardSuit;
    }

    public String toString() {
        return face + " of " + suit;
    }

    public Face getFace() {
        return face;
    }

    public Suit getSuit() {
        return suit;
    }

}
