definition(
    name: "Vacation Lighting",
    namespace: "tkreiner",
    author: "Tom Kreiner",
    description: "Manage random lighting schedules while on vacation",
    category: "Convenience",
    iconUrl: "",
    iconX2Url: "",
    documentationLink: "https://github.com/tkreiner/hubitat_vacation_lighting",
    importUrl: "https://raw.githubusercontent.com/tkreiner/hubitat_vacation_lighting/main/app/hubitat_vacation_lighting.groovy"
)

preferences {
    page(name: "mainPage", title: "Vacation Lighting", install: true, uninstall: true) {
        section {
            paragraph "Welcome to the Vacation Lighting app."
        }
        section("Select a Mode") {
            paragraph "Select the mode that will be used to activate this lighting."
        }
    }
}

// Called when app first installed
def installed() {
  // for now, just write entry to "Logs" when it happens:
    log.trace "installed()"
}

// Called when user presses "Done" button in app
def updated() {
    log.trace "updated()"
}

// Called when app uninstalled
def uninstalled() {
   log.trace "uninstalled()"
   // Most apps would not need to do anything here
}
