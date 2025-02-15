-----------------------------------------------------
------------------- . 관리자   --------------------
-----------------------------------------------------
-- 1. 상품 목록 제품 조회용 쿼리문
		SELECT PRODUCT_NO
		     , CATEGORY_NAME
		     , PRODUCT_NAME
		     , DISCOUNT
		     , PRO_CREATE_AT
		FROM PRODUCT_INFO
		JOIN CATEGORY USING (CATEGORY_NO);

-- 2_1. 상품 정보 등록용 쿼리문
INSERT INTO PRODUCT_INFO (PRODUCT_NO
                                                        , PRODUCT_NAME
                                                        , PRODUCT_DESCRIPTION
                                                        , CATEGORY_NO
                                                        , PRICE
                                                        , PRODUCT_SIZE
                                                        , MATERIAL
                                                        , COLOR
                                                        , ASSEMBLY_YN
                                                        , DISCOUNT
                                                        , COUNTRY)
VALUES(SEQ_PNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)

-- 2_2. 상품 이미지 첨부파일 등록용 쿼리문
INSERT INTO PRODUCT_IMAGE (IMAGE_NO
                                                           , REF_PNO
                                                           , IMG_ORIGINAL_FILE
                                                           , IMG_SAVE_FILE
                                                           , IMAGE_URL
                                                           , THUMBNAIL)
VALUES (SEQ_IMG_PNO.NEXTVAL, SEQ_PNO.CURRVAL, ?, ?, ?, ?)


-- 3. 상품 목록 조회용 쿼리문
SELECT PRODUCT_NO
              , CATEGORY_NAME
              , PRODUCT_NAME
              , PRICE
              , PRODUCT_QUANTITY
FROM PRODUCT_INFO
JOIN CATEGORY USING (CATEGORY_NO);

-- 4. 상품 총 갯수 조회용 쿼리문
SELECT COUNT(*) AS COUNT
FROM PRODUCT_INFO




-- [고객]
-- 1. 상품 전체 갯수 조회용 쿼리문
SELECT *
FROM (
    SELECT ROWNUM RNUM, A.*
    FROM (
        SELECT 
            p.PRODUCT_NAME,
            p.PRICE,
            i.IMAGE_URL || i.IMG_SAVE_FILE AS "TITLEIMG"
        FROM 
            PRODUCT_INFO p
        LEFT JOIN 
            PRODUCT_IMAGE i ON p.PRODUCT_NO = i.REF_PNO
        ORDER BY 
            p.PRODUCT_NO DESC  -- 원하는 정렬 기준으로 변경 가능
    ) A
) 
WHERE RNUM BETWEEN 1 AND 2;


SELECT p.*
FROM PRODUCT_INFO p
LEFT JOIN PRODUCT_IMAGE i ON p.PRODUCT_NO = i.REF_PNO;






-- 2. 상품 상세 조회용 쿼리문
SELECT 
        PRODUCT_NO,
        PRODUCT_NAME,
        CATEGORY_NAME,
        PRICE,
        PRICE * (1 - DISCOUNT / 100) AS DISCOUNTED_PRICE,  -- 할인판매가 계산
        PRODUCT_DESCRIPTION,
        PRODUCT_SIZE,
        MATERIAL,
        COLOR,
        ASSEMBLY_YN,
        DISCOUNT,
        COUNTRY
    FROM 
        PRODUCT_INFO
    JOIN 
        CATEGORY USING (CATEGORY_NO)
    WHERE 
        PRODUCT_NO = ?

-- 3. 상품 첨부파일 조회용 쿼리문
SELECT IMAGE_NO
              , IMG_ORIGINAL_FILE
              , IMG_SAVE_FILE
              , IMAGE_URL
FROM PRODUCT_IMAGE
WHERE REF_PNO = ?





		SELECT IMAGE_NO
             , IMG_ORIGINAL_FILE
             , IMG_SAVE_FILE
             , IMAGE_URL
		FROM PRODUCT_IMAGE
		WHERE REF_PNO = 25;


-- 상품 상세조회용 쿼리문(관리자) - 내용물 조회
SELECT PRODUCT_NO
              , CATEGORY_NAME
              , PRODUCT_NAME
              , PRICE
              , PRODUCT_QUANTITY
              , PRODUCT_DESCRIPTION
              , PRODUCT_SIZE
              , MATERIAL
              , COLOR
              , ASSEMBLY_YN
              , DISCOUNT
              , COUNTRY
              , PRO_CREATE_AT
FROM PRODUCT_INFO
JOIN CATEGORY USING (CATEGORY_NO)
WHERE PRODUCT_NO = ?

-- 상품 상세조회용 쿼리문(관리자)  - 등록된 이미지들 조회
SELECT IMAGE_NO
              , IMG_ORIGINAL_FILE
              , IMG_SAVE_FILE
              , IMAGE_URL
FROM PRODUCT_IMAGE
WHERE REF_PNO = ?


-- 상품 정보 수정용 쿼리문 
UPDATE PRODUCT_INFO
SET PRODUCT_NAME =?
      , CATEGORY_NO =?
      , PRICE =?
      , PRODUCT_QUANTITY =?
      , PRODUCT_DESCRIPTION =?
      , PRODUCT_SIZE =?
      , MATERIAL =?
      , COLOR =?
      , ASSEMBLY_YN =?
      , DISCOUNT =?
      , COUNTRY =?
WHERE PRODUCT_NO = ?



<!-- case1. 첨부파일 수정용 쿼리문 -->
	<entry key="updateAttachment">
		UPDATE ATTACHMENT
		SET ORIGIN_NAME = ?
		  , CHANGE_NAME = ?
		  , UPLOAD_DATE = SYSDATE
		WHERE FILE_NO = ?
	</entry>
	
    
UPDATE PRODUCT_IMAGE
SET IMG_ORIGINAL_FILE = ?
      , IMG_SAVE_FILE = ?
WHERE IMAGE_NO = ?
     

	<!-- case2. 첨부파일 수정용 쿼리문 -->
	<entry key="insertNewAttachment">
		INSERT INTO ATTACHMENT(FILE_NO)
		                     , REF_BNO
		                     , ORIGIN_NAME
		                     , CHANGE_NAMLE
		                     , FILE_PATH
		VALUES(SEQ_FNO.NEXTVAL
		     , ?
		     , ?
		     , ?
		     , ? )
	</entry>	


INSERT INTO PRODUCT_IMAGE (IMAGE_NO
                                                            , REF_PNO
                                                            , IMG_ORIGINAL_FILE
                                                            , IMG_SAVE_FILE
                                                            , IMAGE_URL)
VALUES(SEQ_IMG_PNO.NEXTVAL, 1, 'HI', '54321.jpg', '/resout/path');


-- 카테고리별 침대 상품 목록 조회
SELECT P.PRODUCT_NO,
                p.PRODUCT_NAME,
                p.PRICE,
                i.IMAGE_URL || i.IMG_SAVE_FILE AS "TITLEIMG"
FROM PRODUCT_INFO p
JOIN PRODUCT_IMAGE i ON (p.PRODUCT_NO = i.REF_PNO)
WHERE CATEGORY_NO = 1
AND THUMBNAIL = 'Y';
                
                
                
		SELECT p.PRODUCT_NO,
               p.PRODUCT_NAME,
               p.PRICE,
               i.IMAGE_URL || i.IMG_SAVE_FILE AS "TITLEIMG"
		FROM PRODUCT_INFO p
		JOIN PRODUCT_IMAGE i ON (p.PRODUCT_NO = i.REF_PNO)
		WHERE CATEGORY_NO = 6
		AND THUMBNAIL = 'Y'; 
                
                
                
--
		SELECT p.PRODUCT_NO,
               p.PRODUCT_NAME,
               p.PRICE, 
			   i.IMAGE_URL || i.IMG_SAVE_FILE AS "TITLEIMG" 
	    FROM PRODUCT_INFO p
	    JOIN PRODUCT_IMAGE i ON (p.PRODUCT_NO = i.REF_PNO)
	    WHERE i.THUMBNAIL = 'Y'
	    ORDER BY PRICE ASC
            
SELECT P.PRODUCT_NO,
       P.PRODUCT_NAME,
       P.PRICE,
       I.IMAGE_URL || I.IMG_SAVE_FILE AS "TITLEIMG"
FROM PRODUCT_INFO P
JOIN PRODUCT_IMAGE I ON (P.PRODUCT_NO = I.REF_PNO)
WHERE I.THUMBNAIL = 'Y'
  AND P.PRODUCT_NAME LIKE '%' || ? || '%'

   
   
   
   
-- 찜 상품 추가 쿼리문
INSERT INTO HEART_PRODUCT
VALUE(?, ?)
   
   
DELETE FROM wishlist WHERE userNo = ? AND productNo = ?



DELETE FROM HEART_PRODUCT
WHERE USER_ID = 204
AND PRODUCT_ID = 8;

COMMIT;

-- 찜상품 목록 조회 쿼리문
		SELECT PRODUCT_NO
			 , PRODUCT_NAME
             , PRICE
             , IMAGE_URL || IMG_SAVE_FILE AS "TITLEIMG"
		FROM PRODUCT_INFO P
		JOIN PRODUCT_IMAGE I ON (P.PRODUCT_NO = I.REF_PNO)
		JOIN HEART_PRODUCT H ON ( P.PRODUCT_NO = H.PRODUCT_ID)
		WHERE I.THUMBNAIL = 'Y'
		AND H.USER_ID = 204;


SELECT * FROM PRODUCT_IMAGE
WHERE THUMBNAIL = 'Y';



SELECT 
    P.PRODUCT_NO,
    P.PRODUCT_NAME,
    P.PRICE,
    I.IMAGE_URL || I.IMG_SAVE_FILE AS TITLEIMG
FROM 
    PRODUCT_INFO P
JOIN 
    HEART_PRODUCT H ON (P.PRODUCT_NO = H.PRODUCT_ID)
JOIN 
    PRODUCT_IMAGE I ON (P.PRODUCT_NO = I.REF_PNO AND I.THUMBNAIL = 'Y')
WHERE 
    H.USER_ID = 204;



SELECT COUNT(*) FROM HEART_PRODUCT 
WHERE USER_ID = ?
AND PRODUCT_ID = ?


DELETE FROM PRODUCT_INFO
WHERE PRODUCT_NO = 16;
   
                
                