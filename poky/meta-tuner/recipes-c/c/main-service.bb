SUMMARY = "Mi programa personalizado y servicio systemd"
LICENSE = "CLOSED"

SRC_URI = "file://main.c"

S = "${WORKDIR}"

do_compile() {
    ${CC} ${LDFLAGS} -o main ${S}/main.c
}


do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/main ${D}${bindir}/main

}

pkg_postinst:${PN} () {
    echo "::respawn:/usr/bin/main" >> $D${sysconfdir}/inittab
}

FILES:${PN} += " \
    ${bindir}/main \
"
