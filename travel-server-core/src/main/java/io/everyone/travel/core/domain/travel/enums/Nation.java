package io.everyone.travel.core.domain.travel.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Set;

import static io.everyone.travel.core.domain.travel.enums.Nation.Group.*;

/**
 * ISO 표준에 따른 국가 코드를 정의한다.
 * 참조링크: https://datago.kr/post/2
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Nation {

    GH("가나", "GHANA"),
    GA("가봉", "GABON"),
    GY("가이아나", "GUYANA"),
    GM("감비아", "GAMBIA"),
    GG("건지 섬", "GUERNSEY"),
    GP("과들루프", "GUADELOUPE"),
    GT("과테말라", "GUATEMALA"),
    GU("괌", "GUAM"),
    GD("그레나다", "GRENADA"),
    GR("그리스", "GREECE"),
    GL("그린란드", "GREENLAND"),
    GN("기니", "GUINEA"),
    GW("기니비사우", "GUINEA-BISSAU"),
    NA("나미비아", "NAMIBIA"),
    NR("나우루", "NAURU"),
    NG("나이지리아", "NIGERIA"),
    AQ("남극", "ANTARCTICA"),
    SS("남수단", "REPUBLIC OF SOUTH SUDAN"),
    ZA("남아프리카 공화국", "SOUTH AFRICA"),
    NL("네덜란드", "NETHERLANDS"),
    AN("네덜란드령 안틸레스", "NETHERLANDS ANTILLES"),
    NP("네팔", "NEPAL"),
    NO("노르웨이", "NORWAY"),
    NF("노퍽 섬", "NORFOLK ISLAND"),
    NC("누벨칼레도니", "NEW CALEDONIA"),
    NZ("뉴질랜드", "NEW ZEALAND"),
    NU("니우에", "NIUE"),
    NE("니제르", "NIGER"),
    NI("니카라과", "NICARAGUA"),
    KR("대한민국", "KOREA, REPUBLIC OF"),
    DK("덴마크", "DENMARK"),
    DO("도미니카 공화국", "DOMINICAN REPUBLIC"),
    DM("도미니카 연방", "DOMINICA"),
    DE("독일", "GERMANY"),
    TL("동티모르", "EAST TIMOR"),
    LA("라오스", "LAO PEOPLE'S DEMOCRATIC REPUBLIC"),
    LR("라이베리아", "LIBERIA"),
    LV("라트비아", "LATVIA"),
    RU("러시아", "RUSSIAN FEDERATION"),
    LB("레바논", "LEBANON"),
    LS("레소토", "LESOTHO"),
    RE("레위니옹", "REUNION"),
    RO("루마니아", "ROMANIA"),
    LU("룩셈부르크", "LUXEMBOURG"),
    RW("르완다", "RWANDA"),
    LY("리비아", "LIBYAN ARAB JAMAHIRIYA"),
    LT("리투아니아", "LITHUANIA"),
    LI("리히텐슈타인", "LIECHTENSTEIN"),
    MG("마다가스카르", "MADAGASCAR"),
    MQ("마르티니크", "MARTINIQUE"),
    MH("마셜 제도", "MARSHALL ISLANDS"),
    YT("마요트", "MAYOTTE"),
    MO("마카오", "MACAU"),
    MK("마케도니아 공화국", "REPUBLIC OF MACEDONIA"),
    MW("말라위", "MALAWI"),
    MY("말레이시아", "MALAYSIA"),
    ML("말리", "MALI"),
    IM("맨 섬", "ISLE OF MAN"),
    MX("멕시코", "MEXICO"),
    MC("모나코", "MONACO"),
    MA("모로코", "MOROCCO"),
    MU("모리셔스", "MAURITIUS"),
    MR("모리타니", "MAURITANIA"),
    MZ("모잠비크", "MOZAMBIQUE"),
    ME("몬테네그로", "MONTENEGRO"),
    MS("몬트세랫", "MONTSERRAT"),
    MD("몰도바", "MOLDOVA, REPUBLIC OF"),
    MV("몰디브", "MALDIVES"),
    MT("몰타", "MALTA"),
    MN("몽골", "MONGOLIA"),
    US("미국", "UNITED STATES"),
    UM("미국령 군소 제도", "UNITED STATES MINOR OUTLYING ISLANDS"),
    VI("미국령 버진아일랜드", "VIRGIN ISLANDS, U.S."),
    MM("미얀마", "MYANMAR"),
    FM("미크로네시아 연방", "MICRONESIA"),
    VU("바누아투", "VANUATU"),
    BH("바레인", "BAHRAIN"),
    BB("바베이도스", "BARBADOS"),
    VA("바티칸 시국", "VATICAN CITY STATE"),
    BS("바하마", "BAHAMAS"),
    BD("방글라데시", "BANGLADESH"),
    BM("버뮤다", "BERMUDA"),
    BJ("베냉", "BENIN"),
    VE("베네수엘라", "VENEZUELA"),
    VN("베트남", "VIET NAM"),
    BE("벨기에", "BELGIUM"),
    BY("벨라루스", "BELARUS"),
    BZ("벨리즈", "BELIZE"),
    BA("보스니아 헤르체고비나", "BOSNIA HERCEGOVINA"),
    BW("보츠와나", "BOTSWANA"),
    BO("볼리비아", "BOLIVIA"),
    BI("부룬디", "BURUNDI"),
    BF("부르키나파소", "BURKINA FASO"),
    BV("부베 섬", "BOUVET ISLAND"),
    BT("부탄", "BHUTAN"),
    MP("북마리아나 제도", "NORTHERN MARIANA ISLANDS"),
    BG("불가리아", "BULGARIA"),
    BR("브라질", "BRAZIL"),
    BN("브루나이", "BRUNEI DARUSSALAM"),
    WS("사모아", "SAMOA"),
    SA("사우디아라비아", "SAUDI ARABIA"),
    GS("사우스조지아 사우스샌드위치 제도", "SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS"),
    SM("산마리노", "SAN MARINO"),
    ST("상투메 프린시페", "SAO TOME AND PRINCIPE"),
    PM("생피에르 미클롱", "ST. PIERRE AND MIQUELON"),
    EH("서사하라", "WESTERN SAHARA"),
    SN("세네갈", "SENEGAL"),
    RS("세르비아", "SERBIA"),
    SC("세이셸", "SEYCHELLES"),
    LC("세인트루시아", "SAINT LUCIA"),
    VC("세인트빈센트 그레나딘", "SAINT VINCENT AND THE GRENADINES"),
    KN("세인트키츠 네비스", "SAINT KITTS AND NEVIS"),
    SH("세인트헬레나", "ST. HELENA"),
    SO("소말리아", "SOMALIA"),
    SB("솔로몬 제도", "SOLOMON ISLANDS"),
    SD("수단", "SUDAN"),
    SR("수리남", "SURINAME"),
    LK("스리랑카", "SRI LANKA"),
    SJ("스발바르 얀마옌", "SVALBARD AND JAN MAYEN ISLANDS"),
    SZ("스와질란드", "SWAZILAND"),
    SE("스웨덴", "SWEDEN"),
    CH("스위스", "SWITZERLAND"),
    ES("스페인", "SPAIN"),
    SK("슬로바키아", "SLOVAKIA"),
    SI("슬로베니아", "SLOVENIA"),
    SY("시리아", "SYRIAN ARAB REPUBLIC"),
    SL("시에라리온", "SIERRA LEONE"),
    SG("싱가포르", "SINGAPORE"),
    AE("아랍에미리트", "UNITED ARAB EMIRATES"),
    AW("아루바", "ARUBA"),
    AM("아르메니아", "ARMENIA"),
    AR("아르헨티나", "ARGENTINA"),
    AS("아메리칸사모아", "AMERICAN SAMOA"),
    IS("아이슬란드", "ICELAND"),
    HT("아이티", "HAITI"),
    IE("아일랜드", "IRELAND"),
    AZ("아제르바이잔", "AZERBAIJAN"),
    AF("아프가니스탄", "AFGHANISTAN"),
    AD("안도라", "ANDORRA"),
    AL("알바니아", "ALBANIA"),
    DZ("알제리", "ALGERIA"),
    AO("앙골라", "ANGOLA"),
    AG("앤티가 바부다", "ANTIGUA AND BARBUDA"),
    AI("앵귈라", "ANGUILLA"),
    ER("에리트레아", "ERITREA"),
    EE("에스토니아", "ESTONIA"),
    EC("에콰도르", "ECUADOR"),
    ET("에티오피아", "ETHIOPIA"),
    SV("엘살바도르", "EL SALVADOR"),
    GB("영국", "UNITED KINGDOM"),
    VG("영국령 버진아일랜드", "VIRGIN ISLANDS, BRITISH"),
    IO("영국령 인도양 지역", "BRITISH INDIAN OCEAN TERRITORY"),
    YE("예멘", "YEMEN, REPUBLIC OF"),
    OM("오만", "OMAN"),
    AU("오스트레일리아", "AUSTRALIA"),
    AT("오스트리아", "AUSTRIA"),
    HN("온두라스", "HONDURAS"),
    AX("올란드 제도", "ALAND ISLANDS"),
    WF("왈리스 퓌튀나", "WALLIS AND FUTUNA ISLANDS"),
    JO("요르단", "JORDAN"),
    UG("우간다", "UGANDA"),
    UY("우루과이", "URUGUAY"),
    UZ("우즈베키스탄", "UZBEKISTAN"),
    UA("우크라이나", "UKRAINE"),
    IQ("이라크", "IRAQ"),
    IR("이란", "IRAN"),
    IL("이스라엘", "ISRAEL"),
    EG("이집트", "EGYPT"),
    IT("이탈리아", "ITALY"),
    IN("인도", "INDIA"),
    ID("인도네시아", "INDONESIA"),
    JP("일본", "JAPAN"),
    JM("자메이카", "JAMAICA"),
    ZM("잠비아", "ZAMBIA"),
    JE("저지 섬", "JERSEY"),
    GQ("적도 기니", "EQUATORIAL GUINEA"),
    KP("조선민주주의인민공화국", "KOREA, DEMOCRATIC PEOPLE'S REPUBLIC OF"),
    GE("조지아", "GEORGIA"),
    CF("중앙아프리카 공화국", "CENTRAL AFRICAN REPUBLIC"),
    TW("중화민국", "TAIWAN, PROVINCE OF CHINA"),
    CN("중화인민공화국", "CHINA"),
    DJ("지부티", "DJIBOUTI"),
    GI("지브롤터", "GIBRALTAR"),
    ZW("짐바브웨", "ZIMBABWE"),
    TD("차드", "CHAD"),
    CZ("체코", "CZECH REPUBLIC"),
    CL("칠레", "CHILE"),
    CM("카메룬", "CAMEROON"),
    CV("카보베르데", "CAPE VERDE"),
    KZ("카자흐스탄", "KAZAKHSTAN"),
    QA("카타르", "QATAR"),
    KH("캄보디아", "CAMBODIA"),
    CA("캐나다", "CANADA"),
    KE("케냐", "KENYA"),
    KY("케이맨 제도", "CAYMAN ISLANDS"),
    KM("코모로", "COMOROS"),
    CR("코스타리카", "COSTA RICA"),
    CC("코코스 제도", "COCOS ISLANDS"),
    CI("코트디부아르", "COTE D'IVOIRE"),
    CO("콜롬비아", "COLOMBIA"),
    CG("콩고 공화국", "CONGO"),
    CD("콩고 민주 공화국", "DEMOCRATIC REPUBLIC OF THE CONGO"),
    CU("쿠바", "CUBA"),
    KW("쿠웨이트", "KUWAIT"),
    CK("쿡 제도", "COOK ISLANDS"),
    HR("크로아티아", "CROATIA"),
    CX("크리스마스 섬", "CHRISTMAS ISLAND"),
    KG("키르기스스탄", "KYRGYZSTAN"),
    KI("키리바시", "KIRIBATI"),
    CY("키프로스", "CYPRUS"),
    TH("타이", "THAILAND"),
    TJ("타지키스탄", "TAJIKISTAN"),
    TZ("탄자니아", "TANZANIA, UNITED REPUBLIC OF"),
    TC("터크스 케이커스 제도", "TURKS AND CAICOS ISLANDS"),
    TR("터키", "TURKEY"),
    TG("토고", "TOGO"),
    TK("토켈라우", "TOKELAU"),
    TO("통가", "TONGA"),
    TM("투르크메니스탄", "TURKMENISTAN"),
    TV("투발루", "TUVALU"),
    TN("튀니지", "TUNISIA"),
    TT("트리니다드 토바고", "TRINIDAD AND TOBAGO"),
    PA("파나마", "PANAMA"),
    PY("파라과이", "PARAGUAY"),
    PK("파키스탄", "PAKISTAN"),
    PG("파푸아 뉴기니", "PAPUA NEW GUINEA"),
    PW("팔라우", "PALAU"),
    PS("팔레스타인", "PALESTINE"),
    FO("페로 제도", "FAROE ISLANDS"),
    PE("페루", "PERU"),
    PT("포르투갈", "PORTUGAL"),
    FK("포클랜드 제도", "FALKLAND ISLANDS"),
    PL("폴란드", "POLAND"),
    PR("푸에르토리코", "PUERTO RICO"),
    FR("프랑스", "FRANCE"),
    GF("프랑스령 기아나", "FRENCH GUIANA"),
    TF("프랑스령 남부와 남극 지역", "FRENCH SOUTHERN TERRITORIES"),
    PF("프랑스령 폴리네시아", "FRENCH POLYNESIA"),
    FJ("피지", "FIJI"),
    FI("핀란드", "FINLAND"),
    PH("필리핀", "PHILIPPINES"),
    PN("핏케언 제도", "PITCAIRN"),
    HM("허드 맥도널드 제도", "HEARD AND MC DONALD ISLANDS"),
    HU("헝가리", "HUNGARY"),
    HK("홍콩", "HONG KONG");

    private final String code;

    private final String kr;

    private final String eng;

    Nation(String kr, String eng) {
        this.code = this.name();
        this.kr = kr;
        this.eng = eng;
    }

    public boolean isEastAsia(Nation nation) {
        return EAST_ASIA.contains(nation);
    }


    public static class Group {
        public static final Set<Nation> EAST_ASIA = Set.of(KR, JP, CN);
    }

}
