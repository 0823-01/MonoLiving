--

INSERT INTO MEMBER (USER_NO
                  , USER_ID
                  , USER_PWD
                  , USER_NAME
                  , GENDER
                  , USER_BIRTH
                  , EMAIL
                  , PHONE
                  , ADDRESS
                  , ENFOLL_DATE
                  , STATUS)
VALUES (SEQ_MNO.NEXTVAL
      , ?
      , ?
      , ?
      , ?
      , ?
      , ?
      , ?
      , ?
      , SYSDATE
      , DEFAULT);