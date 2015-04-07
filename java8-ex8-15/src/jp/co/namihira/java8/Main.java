/*
 * Copyright 2015 Kohsuke Namihira All Rights Reserved.
 */
/*
 * Q.
 * Files.linesとPattern.asPredicateを使用して、正規表現に一致する
 * すべての行を表示し、grepユーティリティのように動作するプログラムを
 * 書きなさい。
 */
/*
 * 参考：
 *  - 戦争と平和：http://www.gutenberg.org/ebooks/2600
　*/

package jp.co.namihira.java8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    final static String REGEX_INPUT = "(?<regex>[\\p{L}]+)\\s(?<filePath>.+)";

    public static void main(String[] args){
        final String input = getRegexFromConsole();

        final Matcher inputMatcher = Pattern.compile(REGEX_INPUT).matcher(input);
        String regex = null;
        String filePath = null;
        if (inputMatcher.matches()) {
            regex = inputMatcher.group("regex");
            filePath = inputMatcher.group("filePath");
        }
        Objects.requireNonNull(regex);
        Objects.requireNonNull(filePath);

        final Pattern pattern = Pattern.compile(regex);
        final Path path = Paths.get(filePath);

        try (final Stream<String> lines = Files.lines(path)) {
            lines.filter(l -> pattern.asPredicate().test(l))
                 .forEachOrdered(System.out::println);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static String getRegexFromConsole() {
        try {
           System.out.println("grep [regex] [filepath]");
           System.out.print("grep ");
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
   }

}

/*
 * - log
grep [regex] [filepath]
grep peace C:/Users/Kosuke/workspace/java8-ex8-15/bin/war_and_peace.txt
"Yes, I have heard of his scheme for perpetual peace, and it is very
right light.... In my opinion perpetual peace is possible but--I do not
-to establish peace in Europe on firm foundations--has now decided him
noble soul peacefully to leave this..."
allow him a moment's peace! Here, Pierre, tell them your opinion," said
quickly to her own room to make out the problem in peace. The old man
that the Corsican monster who is destroying the peace of Europe may be
Himself, for in Him alone is truth and peace," said she in a voice
the pine forests veiled in the mist of their summits... There was peace
projects for peace, a secret peace concluded separately."
your regiment before peace is concluded, or you will share defeat and
"Well, is it true that it's peace and capitulation?" asked Nesvitski.
negotiations for peace were already proceeding, and that he therefore
to the Russian line to announce the peace negotiations and to offer the
peace but did not believe in its possibility; others talked of a battle
serene as if all this were happening at home awaiting peaceful
busy with similar peaceful affairs--near the shelter of the regimental
battle and were engaged in peaceful occupations, the cavalry feeding the
to renounce forever these vile fancies, so as peacefully to fulfill Thy
actuated by a real desire for peace.
French troops who are being raised in France will join us, and the peace
gliding slowly across it. "How quiet, peaceful, and solemn; not at all
that. But even it does not exist, there is nothing but quiet and peace.
old miller had been accustomed to sit in his tasseled cap peacefully
so many years Moravians in shaggy caps and blue jackets had peacefully
The quiet home life and peaceful happiness of Bald Hills presented
had followed, and only the heavens promised peace. Toward morning all
of eternal peace and blessedness?" she thought.
peace of mind. Can anything in the world make her or me less a prey to
peace."
"Life as it is leaves one no peace. I should be thankful to do nothing,
"So that's what I'm sorry for--human dignity, peace of mind, purity, and
such peace all around, such blessedness, that one don't want to come
narrow, unchanging frame, he experienced the same sense of peace, of
French colonel on the road, Rostov had maintained with heat that peace
following day. On that day, June 27, the preliminaries of peace were
naturally turned on the peace. The officers, his comrades, like most of
the army, were dissatisfied with the peace concluded after the battle of
perfect peace of mind in spite of my wife's absence. At the time I gave
young, and in nobody's way if only they leave her in peace." But however
much they left her in peace she could not now be at peace, and
peace and settled. I know that no better man than he exists, and I am
married. She said she could lie down in her grave peacefully if that
at home in a quiet haven. In Moscow he felt at peace, at home, warm and
"Spies, traitors, traitors everywhere! Not a moment's peace in my own
could not have a moment's peace and could not die quietly.
or any of the joys of life, and expected peace only "yonder." She
* Death gives relief and death is peaceful.
will. One wants to do it peacefully and lovingly. You're a clever girl
a thing as other people's happiness and peace, and that you are ruining
Napoleon) only for the purpose of securing an armed peace, the French
contemporaries, were carried on with a sincere desire to attain peace,
believed in the possibility of peace and worked zealously to that end,
"To enter Russia without declaring war! I will not make peace as long as
he would not make peace so long as a single armed Frenchman remained on
peace so long as a single armed enemy remained on Russian soil and told
trim, almost like that of peacetime maneuvers, but with a shade of
was firmly persuaded that he wished for peace and intended to enter into
Emperor Alexander wished for peace, but would not enter into
"I desire peace, no less than the Emperor Alexander," he began. "Have I
hurriedly, evidently no longer trying to show the advantages of peace
"I hear you have made peace with Turkey?"
"Yes, I know you have made peace with the Turks without obtaining
They are neither fit for war nor peace! Barclay is said to be the most
peace with you. As for the Swedes--it is their fate to be governed by
reasonable thing left to do is to conclude peace as soon as possible,
reasons of state, was in favor of peace.
others as ninety-nine to one, consisted of men who desired neither peace
rural joys and peace such as he had never known before, and which now
"In peace let us pray unto the Lord."
"For the peace that is from above, and for the salvation of our souls."
give them all, and her too, peace and happiness. And it seemed to her
no one will ever know it." And his soul felt calm and peaceful.
peace and the abolition of war, and secondly, by the fact that when he
arrival and of thanksgiving for the conclusion of peace with the Turks--
to celebrate the signing of peace with the Turks, and the crowd rushed
"No peace, damn them!" he muttered, angry he knew not with whom. "Ah
the present! Quicker, quicker--and that they should leave me in peace!"
There is a rumor that you are thinking of peace. God forbid that you
should make peace after all our sacrifices and such insane retreats! You
that the man who advocates the conclusion of a peace, and that the
salons is unchanging. Since the year 1805 we had made peace and had
terminated by peace.
demonstrations which would very soon end in peace, and the view
quicker.... I wished to be at peace.... And what will become of me? What
use will peace be when he is no longer here?" Princess Mary murmured,
away and begged to be left in peace.
war and the peace that had been concluded. "Yes, I have been much
blamed," he said, "both for that war and the peace... but everything
made peace with Turkey and should not have been through with that war.
therefore most terrible thoughts--would give him no peace. He knew that
Helena in the peaceful solitude where he said he intended to devote his
peaceful, and glorious fatherland, I should have proclaimed her
smile on his lips and a peaceful look of delight at her beauty,
of the penthouse. The whole courtyard was permeated by a strong peaceful
so I hold my peace. An ax will be useful, a hunting spear not bad, but a
Moscow I will accept terms of peace worthy of myself and of my people. I
the peace and welfare of all my subjects.' However, I know their
cook themselves meals twice a day. In peacetime it is only necessary to
in peace.
persuaded to make peace. They are burning for the combat," declared this
herself incompetent, but in the depths of her soul she felt at peace--a
peace arising from consciousness of having stifled those personal dreams
felt, sunk deeper than was desirable for his peace of mind. That pale,
"I shall not be at peace till you promise me this."
overtures for peace.
an advantageous peace, or in case of a refusal make a menacing move on
considerations concerning the terms of the anticipated peace.
You, peaceful inhabitants of Moscow, artisans and workmen whom
about it he had found that peace and inner harmony only through the
joyful sensations, and chiefly of the complete peace of mind and inner
from Napoleon proposing peace and falsely dated from Moscow, though
brought by Lauriston, saying that there could be no question of peace.
peace, he said: There can be no peace, for such is the people's will. He
under an obligation to him because when he was sent to make peace with
Turkey in 1811 independently of Kutuzov, and found that peace had
securing that peace was really Kutuzov's; this Chichagov was the first
peaceful refuge where he could recover himself, rest, and think over all
allowed no respite and no peace, and those who had seemed to pity the
to you? Leave me in peace," his looks seemed to say.
"I can't get a moment's peace.... Mary, is that you? Why did you bring
and peacefully.
tolerate mysticism in anyone now). "He seeks only for peace, and only
therefore never be at peace. A stern expression of the lofty, secret
to find seemliness, happiness, and peace in everything, and I should
arm against the disturber of its peace. All Napoleon's allies suddenly
Their theory, suitable for primitive and peaceful periods of history,
has since lived peaceably and harmlessly in society seems less guilty
　*/
