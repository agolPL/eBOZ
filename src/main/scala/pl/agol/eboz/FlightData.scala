package pl.agol.eboz

/**
 * @author Andrzej Goławski
 */
object FlightData {

  val ICAO_FLIGHT_PLAN = """(FPL-DLH4EL-IS
		  						-A320/M-SDIPRWY/S
		  						-EPWA1200
		  						-N0445F380 SOXER UN869 KONAR UT49 RAPET T170 GAPLA T173 KERAX KERAX1L
		  						-EDDF0117 EDFH
		  						-EET/LKAA0037 EDUU0056 EDGG0107 REG/DAIZI OPR/DLH RMK/TCAS DOF/111018
		  						RVR/200 ORGN/EDDFDLHD);"""

  val ADXP_FLIGHT_PLAN = """-TITLE IFPL
  							-BEGIN ADDR
  							-FAC CFMUTACT
  							-FAC EGZYTTTE
  							-FAC EGZYPXFO
  							-FAC EHAMZTZX
  							-END ADDR
  							-ADEP EIDW
  							-ADES EHAM
  							-AOARCID EIN
  							-AOOPR EIN
  							-ARCID EIN604
  							-ARCTYP A320
  							-CEQPT SHGJ1RWXY
  							-EOBD 070614
  							-EOBT 0840
  							-FILTIM 132341
  							-IFPLID AA61117062
  							-ORGNID EINAOCC
  							-ORIGIN -NETWORKTYPE AFTN -FAC EIDWEINU
  							-SEQPT S
  							-WKTRC M
  							-OPR EIN
  							-REG EIDEK
  							-RVR 075
  							-SEL PRFQ
  							-SRC FPL
  							-TTLEET 0056
  							-PBN B2
  							-RFL F370
  							-SPEED N0448
  							-FLTRUL I
  							-FLTTYP S
  							-ROUTE N0448F370 DCT LIFFY UL975 WAL UM16 DOLAS UL603 LAMSO DCT
  							-ALTRNT1 EHRD
  							-EETFIR EGTT 0006
  							-EETFIR EHAA 0047
  							-BEGIN RTEPTS
    							-PT -PTID EIDW
    							-PT -PTID LIFFY
    							-PT -PTID GINIS
    							-PT -PTID NATKO
    							-PT -PTID LYNAS
    							-PT -PTID ROLEX
    							-PT -PTID *1ROL
    							-PT -PTID MALUD
    							-PT -PTID WAL
    							-PT -PTID MCT
    							-PT -PTID DISAL
    							-PT -PTID NAPEX
    							-PT -PTID DOLAS
    							-PT -PTID ENITO
    							-PT -PTID DIBAL
    							-PT -PTID BUKUT
    							-PT -PTID LAMSO
    							-PT -PTID EHAM
  							-END RTEPTS
  							-SID LIFFY2E
  							-DCT EIDW LIFFY
  							-ATSRT UL975 LIFFY WAL
  							-ATSRT UM16 WAL DOLAS
  							-ATSRT UL603 DOLAS LAMSO
  							-DCT LAMSO EHAM"""
}