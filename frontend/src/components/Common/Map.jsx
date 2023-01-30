import React, { useState } from 'react';
import styles from './Map.module.scss';
export default function Map() {
  const [click, setClick] = useState([
    false,
    false,
    false,
    false,
    false,
    false,
    false,
    false,
    false,
    false,
    false,
    false,
    false,
    false,
    false,
    false,
    false,
  ]);
  const [region, setRegion] = useState('');

  return (
    <div className={styles.map}>
      <div className={styles.check}>{region} 지역 보호소 검색</div>
      <svg height="400" version="1.1" width="320" xmlns="http://www.w3.org/2000/svg">
        <path
          className={click[0] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('서울');
            setClick([
              true,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M112,118L118,112L111,108L110,104L111,102L109,100L102,106L98,109L99,113L104,116L110,118L112,118Z"
        ></path>
        <path
          className={click[1] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('부산');
            setClick([
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M232,277L224,284L215,289L213,293L209,298L212,299L217,299L220,303L225,302L231,296L232,290L239,282L235,279Z"
        ></path>
        <path
          className={click[2] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('대구');
            setClick([
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M184,258L180,253L185,251L184,247L191.5,241L185.5,237L187.5,234L193,234L200,232L201.5,235L205.5,241L199.5,247L193,247L189.5,253L191,259L186,256L184,258Z"
        ></path>
        <path
          className={click[3] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('인천');
            setClick([
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M96,109L98,115L102,117L108,119L110,120L107,123.5L98,121L97,122L95,120L96,119L90,110L89,110L89,104L99,105ZM79,105L84,105L85,107L85,111L80,110L79,105Z"
        ></path>
        <path
          className={click[4] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('광주');
            setClick([
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M77,292L78,284L84,283L90,285L94,281L101,288L98,298L92,298L89,300L85,298L85,294L80,291L78,293L77,292Z"
        ></path>
        <path
          className={click[5] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('대전');
            setClick([
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M131,209L131,208L130,203L135,199L122,195L116,204L116,214L118,215L121,211L124,214Z"
        ></path>
        <path
          className={click[6] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('울산');
            setClick([
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M224,258L229.5,251L238,257L241,253L245,257L245,272L241,271L238,278L231,274L232,271L224,267L219,264L222,260Z"
        ></path>
        <path
          className={click[7] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('세종');
            setClick([
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M125,186L129,182L125,178L126,173L121,166L118,170L117,181L123,190Z"
        ></path>
        <path
          className={click[8] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('경기');
            setClick([
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M69,74L76,76L78,74L78,68L81,67L83,64L94,67L98,62L102,60L111,58L114,68L119,68L122,72L128,74L130,79L140,88L140,90L134,94L137,100L135,107L154,116L148,118L150,124L147,129L146,139L144,145L139,146L134,152L127,153L125,159L120,162L109,158L107,161L94,160L98,157L97,153L92,156L93,146L87,147L85,141L91,143L95,137L91,136L89,133L92,131L97,131L97,123L108,125L112,120L120,113L120,112L117,110L112,107L112,105L111,104L113,102L109,98L102,104L89,102L86,96L82,99L71,92L73,87L69,85L71,82L69,79L69,74Z"
        ></path>
        <path
          className={click[9] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('강원');
            setClick([
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M98,60L104,58L113,56L116,68L121,68L124,72L130,74L132,79L142,88L142,90L136,94L139,100L137,107L156,116L150,118L152,124L149,129L148,139L146,145L152,150L152,150L153.5,145L157,137L160,135L161,138L169,141L181,140L175,146L189,147L192,149L199,148L205,153L207,148L211,153L224,150L231,154L239,143L234,139L234,132L225,118L222,108L199,81L180,33L175,34L151,6L147,7L142,4L145,18L137,16L134,22L130,23L131,30L125,29L124,20L121,18L123,8L120,5L115,11L117,17L108,20L106,18L105,24L99,25L93,30L86,31L90,40L87,45L89,47L91,53L98,60Z"
        ></path>
        <path
          className={click[10] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('충북');
            setClick([
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M145,148L152,152L154,152L155,148L157,140L159,138L161,140L169,143L176,142L171.5,148L189,149L192,151L199,150L186,160L184,169L175,167L162,171L163,175L156,175L156,185L151,182L149,185L154,191L155,200L150,207L158,208L164,215L159,217L155,226L146,227L140,224L135,218L136,213L133,209L133,208L132,203L140,199L124,193L127,186L131,182L127,178L128,173L121,163L128,160L128,155L133,155L135,155L140,147Z"
        ></path>
        <path
          className={click[11] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('충남');
            setClick([
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M119,166L116,170L115,181L121,190L119,196L114,204L113,214L118,218L121,214L125,216L132,211L135,218L139,225L134,225L131,231L120,219L115,222L112,220L107,223L102,216L93,216L91,222L80,227L73,221L76,217L73,207L75,206L71,202L75,198L71,190L72,183L68,175L65,182L57,173L65,164L71,168L77,167L74,160L75,153L81,163L88,162L90,174L100,166L101,162.5L106,164L110,161L117,163.5L120,164Z"
        ></path>
        <path
          className={click[12] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('전북');
            setClick([
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M142,227L136,227L131,233L120,221L115,224L112,222L107,225L102,218L93,218L91,224L80,229L77,238L85,238L83,242L84,248L77,249L65,259L78,264L70,268L71,274L75,276L81,273L86,264L95,268L101,267L102,271L101,277L104,279L116,277L122,280L128,275L137,279L139,273L139,265L135,258L138,252L148,237L154,235L155,228L150,230Z"
        ></path>
        <path
          className={click[13] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('전남');
            setClick([
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M68,270L70,276L75,278L81,275L86,266L95,270L101,269L102,273L101,279L104,281L116,279L122,282L128,277L137,281L135,290L144,301L144,310L140,309L138,315L132,313L135,321L141,319L140,328L134,326L133,333L131,333L130,319L120,325L121,329L125,337L115,345L106,339L111,337L111,332L115,334L116,327L105,330L98,333L93,334L94,346L87,347L81,336L78,343L70,355L63,354L63,343L66,341L63,336L71,335L64,326L74,321L69,313L61,319L62,306L58,305L61,300L55,288L63,277L63,273L68,270ZM77,292L78,293L80,291L85,294L85,298L89,300L92,298L98,298L101,288L94,281L90,285L84,283L78,284L77,292Z"
        ></path>
        <path
          className={click[14] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('경북');
            setClick([
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M198,153L188,160L186,171L177,169L165,172L166,177L158,177L158,187L152,185L151,185L156,191L157,200L153,205L161,207L166,216L161,218L157,226L156,236L160,241L168,241L173,253L180,256L178,252L183,250L182,246L189,241L184,238L186,232L193,232L200,230L203,233L207,241L200,249L194,248L191,254L193,259L201,260L207,264L215,258L220,259L229,249L238,255L240,251L246,255L250,240L252,232L248,235L241,235L244,227L242,213L246,176L245,157L241,144L233,154L230,156L224,152L211,155L208,151L206,155.5L200,151L198,153ZM273,145L276,142L278,141L283,140L280,148L276,147L275,145L273,145ZM306,150L310,150L309,152ZM311,152L315,150L314,152Z"
        ></path>
        <path
          className={click[15] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('경남');
            setClick([
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
              false,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M147,301L138,288L141,275L142,264L138,258L142,251L146,244L152,238L155,238L159,243L166,244L172,256L180,258L184,261L186,259L192,262L198,262L208,267L213,262L219,260L216,264L221,268L229,272L229,276L220,284L213,287L205,300L194,296L194,302L184,301L181,307L186,308L181,315L181,318L175,315L168,317L162,313L161,304L157,304L154,309L151,308L146,312Z"
        ></path>
        <path
          className={click[16] ? styles['path-click'] : styles.path}
          onClick={() => {
            setRegion('제주');
            setClick([
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              false,
              true,
            ]);
          }}
          fill="#B3BDF0"
          stroke="#ffffff"
          d="M96,377L105,367L120,368L126,365L134,365L142,370L136,380L127,384L114,386L104,384L100,385L94,377Z"
        ></path>
        <text x="95" y="110" className={styles.text}>
          서울
        </text>
        <text x="220" y="290" className={styles.text}>
          부산
        </text>
        <text x="185" y="240" className={styles.text}>
          대구
        </text>
        <text x="75" y="115" className={styles.text}>
          인천
        </text>
        <text x="80" y="290" className={styles.text}>
          광주
        </text>
        <text x="113" y="205" className={styles.text}>
          대전
        </text>
        <text x="225" y="265" className={styles.text}>
          울산
        </text>
        <text x="113" y="178" className={styles.text}>
          세종
        </text>
        <text x="113" y="135" className={styles.text}>
          경기도
        </text>
        <text x="160" y="90" className={styles.text}>
          강원도
        </text>
        <text x="130" y="170" className={styles.text}>
          충청북도
        </text>
        <text x="80" y="190" className={styles.text}>
          충청남도
        </text>
        <text x="90" y="250" className={styles.text}>
          전라북도
        </text>
        <text x="85" y="315" className={styles.text}>
          전라남도
        </text>
        <text x="190" y="200" className={styles.text}>
          경상북도
        </text>
        <text x="160" y="280" className={styles.text}>
          경상남도
        </text>
        <text x="105" y="378" className={styles.text}>
          제주도
        </text>
      </svg>
    </div>
  );
}
