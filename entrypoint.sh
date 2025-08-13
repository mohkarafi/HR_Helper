#!/usr/bin/env bash

host="$1"
port="$2"

echo "⏳ Waiting for $host:$port to be available..."

while ! nc -z $host $port; do
  sleep 1
done

echo "✅ $host:$port is available. Starting the application..."
exec "${@:3}"p.jar