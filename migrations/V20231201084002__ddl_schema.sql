CREATE TABLE public.rate (
                             id bigserial NOT NULL,
                             base_currency varchar(3) NOT NULL,
                             currency varchar(3) NOT NULL,
                             rate float8 NOT NULL,
                             "timestamp" timestamp(6) NULL,
                             CONSTRAINT rate_pkey PRIMARY KEY (id)
);


CREATE TABLE public."statistics" (
                                     id bigserial NOT NULL,
                                     client varchar(255) NULL,
                                     request_id varchar(255) NULL,
                                     service_name varchar(255) NULL,
                                     "time" timestamp(6) NULL,
                                     CONSTRAINT statistics_pkey PRIMARY KEY (id)
);