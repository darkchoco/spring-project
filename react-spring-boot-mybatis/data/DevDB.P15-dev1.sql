/*******************************************************************************
  react-spring-boot-mybatis
*******************************************************************************/
CREATE TABLE product
(
    product_code SERIAL PRIMARY KEY,
    product_name VARCHAR(128) NOT NULL,
    description  VARCHAR(5000),
    price        NUMERIC(20,2) DEFAULT 0.00,
    filename     VARCHAR(500)
);

INSERT INTO product (product_name, description, price, filename)
VALUES ('The John Eliot Gardiner Collection',
        'Der erfolgreichste Interpret der historischen Aufführungspraxis und unermüdliche Entdecker neuer musikalischer Welten, John Eliot Gardiner, feiert seinen 70. Geburtstag mit der Deutschen Grammophon. Gardiner und die DG verbinden über 20 Jahre einer einzigartigen Zusammenarbeit.
Gardiner selbst hat diese Zusammenstellung seiner wichtigsten Aufnahmen ausgewählt, die jetzt in einer streng limitierten 30 CD-Box erscheint.
Wahrhaft großartige Aufnahmen der Label DG, Archiv Produktion & Philips von Monteverdi, Bach und Händel über Verdi bis Strawinski.

Die Aufnahmen beginnen in den späten 1970er Jahre und bieten einen einzigartigen Überblick über Gardiners gesamte Karriere, seinen Pioniergeist und seine innovative Interpretationen. Gleichzeitig lassen sich zentrale Werke aus Barock, Klassik und Romantik in mustergültigen Referenz-Aufnahmen erleben.
John Eliot Gardiner, der mit mehr Grammophone Preisen als jeder andere lebende Musiker ausgezeichnet wurde, feiert seinen 70. Geburtstag und zeigt keinerlei Ermüdungserscheinungen. Immer wieder begeistert er uns mit Interpretationen, die bekannte Werke in vollkommen neuem Licht erscheinen lassen.

Alle Alben erscheinen mit den originalen Covern. Das über 100-seitige Booklet-Buch enthält u.a. ein ausführliches Interview, das Jonathan Freeman-Attwood, Chef Royal Academy of Music in London, mit Gardiner exklusiv für diese Box führte.

The John Eliot Gardiner Collection – from Monteverdi to Stravinsky',
        109.99,
        'jeg-collection.jpg'),
        ('Alicia de Larrocha Complete Decca Recordings',
        'Decca presents this 41CD set which heralds a significant program of digital releases alongside a strong social campaign by Decca for the revered Spanish pianist Alicia de Larrocha. Thanks to a new collaboration with the Estate, Decca have secured editorial control of her streaming and official social platforms. More on this shortly, but her daughter and biographer are both confirmed as spokespersons.

A Decca artist for nearly 20 years, her interpretations of Spanish music remain the gold standard, but she was equally at home with Bach, Mozart, 19th-century virtuoso fare and the Russian Romantics. Included are previously unreleased recordings of Grieg and Albéniz; 2 CDs of her early Hispavox/EMI Madrid recordings of piano encores; and an excerpt from a new biography of Ms Larrocha by Mònica Pagès.

If you wanted to encounter a ‘Who’s Who’ of New York City-based keyboard titans gathered in one place in the 1970s, you only had to purchase a ticket for an Alicia de Larrocha recital. At her concerts you might encounter the likes of Arthur Rubinstein, Gina Bachauer, Van Cliburn, Claudio Arrau and Vladimir Horowitz. Such was the level of reverence by fellow pianists for her musicianship and sheer technical brilliance.

• The music of Spain is central to this collection, and de Larrocha’s interpretations and characterizations brought new meaning and importance to these works and remain the gold standard – highlights include: Falla’s Nights in the Gardens of Spain, Albéniz’ Ibéria, and Granados’ Goyescas.
• When it came to 19th-century virtuoso fare, key corners of the French repertoire, and the Russian Romantics, de Larrocha positively flourished: The Khachaturian Concerto; Rachmaninov’s Second and Third Concertos; Franck’s Symphonic Variations; Ravel’s Concerto, Grieg’s Piano Sonata [previously unreleased], Mendelssohn’s Variations
• Larrocha’s Schumann playing is fluent and poetic; notice, for example, the Concerto, Kreisleriana, Faschingsschwank aus Wien, the C Major Fantasy, the F-sharp Novelette, plus both analogue and digital versions of the rarely heard B Minor Allegro and the perpetually popular Carnaval',
        89.99,
        'adl-complete-decca-recordings.jpg'),
        ('Karl Böhm: Sämtliche Orchester-Aufnahmen auf DG',
        'Anlässlich des 40.Todestages von Karl Böhm präsentiert die Deutsche Grammophon sämtliche Orchesteraufnahmen für das Label, die zum ersten Mal gebündelt erscheinen. Die Edition präsentiert die kompletten Sinfonie- und Konzertaufnahmen auf 67 CDs + 1 Blu-ray Audio und zusammen mit der bereits veröffentlichten THE OPERAS BOX bildet die Box damit Böhms gesamte Diskographie bei der Deutschen Grammophon ab. Enthalten sind die vollständigen Sinfoniezyklen von Beethoven, Brahms, Mozart & Schubert, Hauptwerke von Richard Strauss, Tschaikowsky und Wagner in prachtvollem Stereoklang und Konzerte mit den Pianisten Emil Gilels und Maurizio Pollini. Zwei CDs mit Sprachbeiträgen von Böhm zu Mozarts Musik sowie eigenen biographischen Anmerkungen und eine weitere CD mit Probenmitschnitten (Schubert) bieten einen faszinierenden Einblick in die Denk- und Arbeitsweise von Böhm. In einem Essay beleuchtet Böhm-Kenner Richard Osborne das Schaffen des Maestro und für die CD-Sleeves wurden die Originalcover reproduziert. Als Bonus bietet eine Blu-ray Audio Disc den kompletten Mozart-Symphonie-Zyklus mit den Berliner Philharmonikern in 24-bit Remastering.',
        211.99,
        'kb-dg.jpg');

SELECT * FROM product;
