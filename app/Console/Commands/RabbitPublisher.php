<?php

namespace App\Console\Commands;

use Illuminate\Console\Command;
use Log;
use PhpAmqpLib\Connection\AMQPStreamConnection;
use PhpAmqpLib\Message\AMQPMessage;


class RabbitPublisher extends Command
{
    /**
     * The name and signature of the console command.
     *
     * @var string
     */
    protected $signature = 'rabbit:publisher';

    /**
     * The console command description.
     *
     * @var string
     */
    protected $description = 'Consume Rabbit';


    /**
     * rabbitConsume constructor.
     * @param Request $request
     */
    public function __construct()
    {
        parent::__construct();


    }

    /**
     * Execute the console command.
     *
     * @return mixed
     */
    public function handle()
    {
        $connection = new AMQPStreamConnection(config('rabbit.host'),
            config('rabbit.port'), config('rabbit.user'), config('rabbit.pwd'));

        $channel = $connection->channel();

        $channel->queue_declare('task_queue', false, true, false, false);

        $argv = array("a"=>"red","b"=>"green","c"=>"blue","d"=>"yellow");
        $data = implode(' ', array_slice($argv, 1));
        if(empty($data)) $data = "Hello World!";
        $msg = new AMQPMessage($data,
            array('delivery_mode' => AMQPMessage::DELIVERY_MODE_PERSISTENT)
        );

        $channel->basic_publish($msg, '', 'task_queue');

        echo " [x] Sent ", $data, "\n";


        $channel->close();
        $connection->close();
        return false;
    }
}
