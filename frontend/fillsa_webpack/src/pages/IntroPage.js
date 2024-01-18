import styles from "./IntroPage.module.css";

const IntroPage = () => {
  return (
    <div className={styles.introPage}>
      <div className={styles.writingWithABigPencil4910Parent}>
        <img
          className={styles.writingWithABigPencil4910Icon}
          alt=""
          src="/writingwithabigpencil49105564090719-1@2x.png"
        />
        <div className={styles.frameParent}>
          <div className={styles.parent}>
            <div className={styles.div}>
              <div className={styles.button}>
                지금 바로 필사를 시작해보세요.
              </div>
            </div>
            <div className={styles.div2}>
              <p
                className={styles.p}
              >{`필사는 외국어 학습에 유익한 방법입니다. `}</p>
              <p className={styles.p1}>
                좋은 글을 따라 쓰면서 어휘력과 문장 구조를 향상시키며, 다양한
                표현과 스타일을 배울 수 있습니다. 더불어 문화적 이해와 자극을
                받아 자기 향상에 도움이 됩니다.
              </p>
            </div>
            <div className={styles.group}>
              <div className={styles.div3}>
                <div className={styles.button}>ai 글감 생성하기</div>
              </div>
              <div className={styles.div3}>
                <div className={styles.button}>인기 글감 둘러보기</div>
              </div>
              <div className={styles.div3}>
                <div className={styles.button}>직접 업로드하기</div>
              </div>
            </div>
          </div>
          <div className={styles.container}>
            <div className={styles.div}>
              <div className={styles.button}>
                <p className={styles.p1}>{`로그인을 하면 `}</p>
                <p className={styles.p1}>
                  더 다양한 서비스를 제공받을 수 있습니다.
                </p>
              </div>
            </div>
            <div className={styles.frameDiv}>
              <div className={styles.div8}>
                <div className={styles.parent1}>
                  <img className={styles.icon} alt="" src="/@2x.png" />
                  <div className={styles.button}>Google 로그인</div>
                </div>
              </div>
              <div className={styles.div8}>
                <div className={styles.parent1}>
                  <img className={styles.icon} alt="" src="/@2x.png" />
                  <div className={styles.button}>Apple 로그인</div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className={styles.fillsa}>fillsa</div>
    </div>
  );
};

export default IntroPage;
