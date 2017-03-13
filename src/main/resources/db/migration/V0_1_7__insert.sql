INSERT INTO cities VALUES
    ('94ccaae6-5cd6-4518-b5a9-c23fd42d0400', 'Lublin'),
    ('7dd27401-518a-4ad9-af2d-97f10bf22ab0', 'Gliwice'),
    ('5e80f157-b26f-419c-a4b3-6da73b8430e4', 'Warszawa');

INSERT INTO addresses VALUES
    ('9c01b45c-9004-403c-8205-f4b42ae16ebf', 'ul. Złota', '5e80f157-b26f-419c-a4b3-6da73b8430e4'),
    ('7334406a-f578-472d-8625-b73e8f446c62', 'ul. Srebrna', '5e80f157-b26f-419c-a4b3-6da73b8430e4'),
    ('936c79f2-ef6f-4d37-91de-c0a9becbaa16', 'ul. Przybylskieg', '5e80f157-b26f-419c-a4b3-6da73b8430e4'),
    ('e4cdf7ea-4e4a-4ee2-acc8-3dee2a5d5e8f', 'ul. Sikorskiego', '94ccaae6-5cd6-4518-b5a9-c23fd42d0400'),
    ('306ed7a4-14f0-4ed1-bf47-e33e6a045d10', 'ul. Okopowa', '5e80f157-b26f-419c-a4b3-6da73b8430e4');

INSERT INTO people VALUES
    ('58e952aa-d60c-4033-88d4-986ff28d10ec', 'Kasia', 27),
    ('cba1b394-7e52-4eef-ad4a-921a1073ecf6', 'Michal', 28);

INSERT INTO addresses_people VALUES
    ('c77d4fd9-6106-4586-9afb-ae9bc622a8c2', '58e952aa-d60c-4033-88d4-986ff28d10ec', '7334406a-f578-472d-8625-b73e8f446c62'),
    ('fbd7722b-f5c4-40a1-b2af-ba18ff6458da', '58e952aa-d60c-4033-88d4-986ff28d10ec', '936c79f2-ef6f-4d37-91de-c0a9becbaa16'),
    ('32500d9f-9a92-4063-ad60-928f23b96b84', '58e952aa-d60c-4033-88d4-986ff28d10ec', 'e4cdf7ea-4e4a-4ee2-acc8-3dee2a5d5e8f'),
    ('85a96c04-bc46-4283-8624-83da2e96cc78', 'cba1b394-7e52-4eef-ad4a-921a1073ecf6', '7334406a-f578-472d-8625-b73e8f446c62'),
    ('334b9c24-e5a7-40eb-a8be-39c2a2099e6a', 'cba1b394-7e52-4eef-ad4a-921a1073ecf6', '9c01b45c-9004-403c-8205-f4b42ae16ebf'),
    ('b9ce9524-edb1-4a45-99e3-50a426a8d01f', 'cba1b394-7e52-4eef-ad4a-921a1073ecf6', '306ed7a4-14f0-4ed1-bf47-e33e6a045d10');
