CREATE TABLE ACCUSATION (
        REACH_ID INT COMMENT '河道ID',
        ID VARCHAR(20) NOT NULL COMMENT '举报ID',
        TYPE CHAR(1) COMMENT '举报类型（大）',
        SMALL_TYPE CHAR(2) COMMENT '举报类型（小）',
        INFORMAT VARCHAR(128) COMMENT '举报ID',
        CONTACT_TYPE CHAR(2) COMMENT '联系类型',
        CONTACT_INFO VARCHAR(1024) COMMENT '联系方式',
        INFO VARCHAR(2048) COMMENT '举报信息',
        ACCESSORY VARCHAR(2048) COMMENT '举报附件',
        ACCESSORY_SMALL VARCHAR(2048) COMMENT '举报附件小图',
        PROPOSAL VARCHAR(1024) COMMENT '建议',
        POSITION VARCHAR(1024) COMMENT '举报位置',
        POST_X VARCHAR(22) COMMENT '位置坐标X',
        POST_Y VARCHAR(22) COMMENT '位置坐标Y',
        CREATE_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '举报时间',
        PRIMARY KEY (ID),
        INDEX FK_REFERENCE_11 (REACH_ID)
)COMMENT='群众举报信息表';