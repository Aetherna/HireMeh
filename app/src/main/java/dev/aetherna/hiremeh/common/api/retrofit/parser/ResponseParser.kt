package dev.aetherna.hiremeh.common.api.retrofit.parser

interface ResponseParser<Response, Data> {
    fun parse(response: Response): Data
}