#!/usr/bin/env bash

sudo -u postgres psql -U postgres -c "DROP DATABASE jooq_notx_db;"
sudo -u postgres psql -U postgres -c "DROP USER IF EXISTS webflux_samples_user;"
sudo -u postgres psql -U postgres -c "CREATE USER webflux_samples_user WITH PASSWORD 'webflux';"
sudo -u postgres psql -U postgres -c "CREATE DATABASE jooq_notx_db OWNER webflux_samples_user;"
sudo -u postgres psql -U postgres -c "GRANT ALL PRIVILEGES ON DATABASE jooq_notx_db to webflux_samples_user;"