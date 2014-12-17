/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */
/**
 * Q.
 * 再度、ストリーム操作を使用して、オフセットに1時間未満の情報が
 * 含まれるすべてのタイムゾーンを見つけなさい。
 */
/**
 * A.
 * 下部ログ参照
 */

package jp.co.namihira.java8;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        // prepare
        final int diffHour = 1;

        // action
        final Set<ZoneId> results =  DateTimeUtils.getZoneIds(diffHour);

        // check
        System.out.println("Count : " + results.size());
        results.stream().forEachOrdered(r -> {
            System.out.println(r + ":" + ZonedDateTime.of(LocalDateTime.now(), r).getOffset());
        });
    }

}

/**
 * Count : 52
 * Africa/Abidjan:Z
 * Africa/Accra:Z
 * Africa/Bamako:Z
 * Africa/Banjul:Z
 * Africa/Bissau:Z
 * Africa/Casablanca:Z
 * Africa/Conakry:Z
 * Africa/Dakar:Z
 * Africa/El_Aaiun:Z
 * Africa/Freetown:Z
 * Africa/Lome:Z
 * Africa/Monrovia:Z
 * Africa/Nouakchott:Z
 * Africa/Ouagadougou:Z
 * Africa/Sao_Tome:Z
 * Africa/Timbuktu:Z
 * America/Danmarkshavn:Z
 * Atlantic/Canary:Z
 * Atlantic/Faeroe:Z
 * Atlantic/Faroe:Z
 * Atlantic/Madeira:Z
 * Atlantic/Reykjavik:Z
 * Atlantic/St_Helena:Z
 * Eire:Z
 * Etc/GMT:Z
 * Etc/GMT+0:Z
 * Etc/GMT-0:Z
 * Etc/GMT0:Z
 * Etc/Greenwich:Z
 * Etc/UCT:Z
 * Etc/UTC:Z
 * Etc/Universal:Z
 * Etc/Zulu:Z
 * Europe/Belfast:Z
 * Europe/Dublin:Z
 * Europe/Guernsey:Z
 * Europe/Isle_of_Man:Z
 * Europe/Jersey:Z
 * Europe/Lisbon:Z
 * Europe/London:Z
 * GB:Z
 * GB-Eire:Z
 * GMT:Z
 * GMT0:Z
 * Greenwich:Z
 * Iceland:Z
 * Portugal:Z
 * UCT:Z
 * UTC:Z
 * Universal:Z
 * WET:Z
 * Zulu:Z
 */
