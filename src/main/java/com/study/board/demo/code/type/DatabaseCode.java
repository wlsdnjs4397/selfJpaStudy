package com.study.board.demo.code.type;

import com.fasterxml.jackson.annotation.JsonValue;

public interface DatabaseCode {
	//DatabaseCode 구현 enum 의 Json 직렬화시에 enum 명 대신에, code 값을 쓰게 하기 위한 설정
	@JsonValue
	String getCode();
}
