<?php
/**
 * Created by PhpStorm.
 * User: Administrator
 * Date: 2018/8/20
 * Time: 14:24
 */
return [
    'host' => env('SERVICE_RABBIT_HOST', '127.0.0.1'),//online
    'port' => env('SERVICE_RABBIT_PORT', 5672),
    'user' => env('SERVICE_RABBIT_USER', 'guest'),
    'pwd'  => env('SERVICE_RABBIT_PWD', 'guest'),
];