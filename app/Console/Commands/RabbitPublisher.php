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

        $queue = 'hello';

        $channel->queue_declare($queue, false, true, false, false);
        echo ' [*] Waiting for messages. To exit press CTRL+C', "\n";
        Log::notice('Rabbit [*] Waiting for messages. To exit press CTRL+C');

        $msg = new AMQPMessage('Hello World!');
        $channel->basic_publish($msg, '', 'hello');

        echo " [x] Sent 'Hello World!'\n";

        $channel->close();
        $connection->close();
        return false;
    }
}
