package com.prancibot.common.dto;

public record ExceptionResponse(String reason, int statusCode, String at) {
}
