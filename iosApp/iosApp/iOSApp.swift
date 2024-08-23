import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        IosAppInitializerKt.initialize()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
